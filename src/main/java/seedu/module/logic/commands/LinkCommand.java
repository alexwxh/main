package seedu.module.logic.commands;

import java.util.List;
import static java.util.Objects.requireNonNull;

import seedu.module.commons.core.Messages;
import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.module.TrackedModule;
import seedu.module.model.module.Link;

/**
 * Adds or launches a Link in a Module depending on input
 */
public class LinkCommand extends Command {
    public static final String COMMAND_WORD = "link";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds or launches a Link to a Module. "
            + "Parameters: " + "Index"
            + "Action" + "t/Link title [l/Link]\n"
            + "Example: " + COMMAND_WORD + "1 " + "a/add "
            + "t/example " + "l/ " + "http://google.com\n"
            + COMMAND_WORD + "1 "+ "a/go " + "t/" + "example\n";

    public static final String MESSAGE_LINK_SUCCESS = "New link added to module.";
    public static final String MESSAGE_LAUNCH_SUCCESS = "Website opened in default browser.";
    public static final String MESSAGE_DUPLICATE_TITLE = "Link name already exists, choose another one.";

    private final Index targetIndex;
    private final Link webLink;
    private final String action;
    private final String linkTitle;

    /**
     * specific to `link add` commands
     * @param action
     * @param targetIndex
     * @param webLink
     */
    public LinkCommand(String action, Index targetIndex, Link webLink) {
        this.action = action;
        this.targetIndex = targetIndex;
        this.webLink = webLink;
        this.linkTitle = null;
    }

    /**
     * specific to `link go` commands
     * @param action
     * @param targetIndex
     * @param linkTitle
     */
    public LinkCommand(String action, Index targetIndex, String linkTitle){
        this.action = action;
        this.targetIndex = targetIndex;
        this.webLink = null;
        this.linkTitle = linkTitle;
    }

    private Link find(TrackedModule targetModule, String linkTitle) throws CommandException{
        for (Link l: targetModule.links){
            if (l.name.equals(linkTitle)){
                return l;
            }
        }
        throw new CommandException("Link with matching title not found");
    }
    @Override
    public CommandResult execute(Model model) throws CommandException{
        requireNonNull(model);
        List<TrackedModule> lastShownList = model.getFilteredModuleList();
        if(action.equals("add")) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
            }
            TrackedModule moduleToAddLink = lastShownList.get(targetIndex.getZeroBased());
            if(moduleToAddLink.hasLinkTitle(webLink)){
                throw new CommandException(MESSAGE_DUPLICATE_TITLE);
            }
            moduleToAddLink.addLink(webLink);
            return  new CommandResult(MESSAGE_LINK_SUCCESS);
        }else if(action.equals("go")){
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
            }
            TrackedModule moduleToAccess = lastShownList.get(targetIndex.getZeroBased());
            Link target = find(moduleToAccess, linkTitle);
            target.launch();
            return new CommandResult((MESSAGE_LAUNCH_SUCCESS));
        }
        throw new CommandException("Error in parsing");
    }

}
