package seedu.module.logic.parser.linkcommandparsers;

import org.junit.jupiter.api.Test;
import seedu.module.logic.commands.linkcommands.LaunchLinkCommand;
import seedu.module.logic.parser.LinkCommandParser;
import seedu.module.logic.parser.exceptions.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class LaunchLinkCommandParserTest {
    private static final String VALID_COMMAND = "go n/website";
    private static final String INVALID_COMMAND = "go";
    private LaunchLinkCommand expectedCommand = new LaunchLinkCommand("website");
    private LinkCommandParser parser = new LinkCommandParser();

    @Test
    public void parse() throws ParseException {
        //valid
        assertEquals(parser.parse(VALID_COMMAND), expectedCommand);
        //invalid missing argument
        assertThrows((ParseException.class), () -> parser.parse(INVALID_COMMAND));
    }
}
