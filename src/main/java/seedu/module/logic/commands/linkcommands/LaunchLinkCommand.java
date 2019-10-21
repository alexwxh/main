package seedu.module.logic.commands.linkcommands;

import seedu.module.commons.core.Messages;
import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.CommandResult;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.module.Link;
import seedu.module.model.module.TrackedModule;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class LaunchLinkCommand extends LinkCommand {
    private final Index targetIndex;
    private final String linkTitle;

    public LaunchLinkCommand(Index targetIndex, String linkTitle){
        this.targetIndex = targetIndex;
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
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TrackedModule> lastShownList = model.getFilteredModuleList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }
        TrackedModule moduleToAccess = lastShownList.get(targetIndex.getZeroBased());
        Link target = find(moduleToAccess, linkTitle);
        target.launch();
        return new CommandResult((MESSAGE_LAUNCH_SUCCESS));
    }

}