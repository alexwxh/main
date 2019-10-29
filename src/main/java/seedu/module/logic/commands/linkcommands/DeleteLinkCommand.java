package seedu.module.logic.commands.linkcommands;

import static java.util.Objects.requireNonNull;

import seedu.module.logic.commands.CommandResult;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.module.TrackedModule;

/**
 * Deletes a Link with matching title from TrackedModule.
 */
public class DeleteLinkCommand extends LinkCommand {
    private final String linkTitle;

    public DeleteLinkCommand(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    /**
     * Deletes Link object with matching title from the TrackedModule
     * @param targetModule
     * @param linkTitle
     * @return
     * @throws CommandException
     */
    private void delete(TrackedModule targetModule, String linkTitle) throws CommandException {
        boolean removed = targetModule.getLink().removeIf(module -> module.name.equals(linkTitle));
        if (!removed) {
            throw new CommandException("Link with matching title not found. No links deleted");
        }
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        TrackedModule moduleToAccess = this.currentDisplayed(model);
        this.delete(moduleToAccess, linkTitle);
        return new CommandResult((MESSAGE_DELETE_SUCCESS), false, true, false);
    }

}
