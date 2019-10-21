package seedu.module.logic.commands.linkcommands;

import java.util.List;
import static java.util.Objects.requireNonNull;

import seedu.module.commons.core.Messages;
import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.Command;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.module.TrackedModule;
import seedu.module.model.module.Link;

/**
 * Adds or launches a Link in a Module depending on input
 */
public abstract class LinkCommand extends Command {
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


}
