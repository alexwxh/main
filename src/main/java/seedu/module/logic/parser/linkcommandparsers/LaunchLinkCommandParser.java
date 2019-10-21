package seedu.module.logic.parser.linkcommandparsers;

import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.linkcommands.LaunchLinkCommand;
import seedu.module.logic.parser.ArgumentMultimap;
import seedu.module.logic.parser.ParserUtil;
import seedu.module.logic.parser.exceptions.ParseException;
import seedu.module.model.module.Link;

import static seedu.module.logic.parser.CliSyntax.PREFIX_TITLE;

public class LaunchLinkCommandParser {
    public LaunchLinkCommand parse(ArgumentMultimap argMultimap) throws ParseException{
        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            String title = argMultimap.getValue(PREFIX_TITLE).get();
            return new LaunchLinkCommand(index, title);
        }else{
            throw new ParseException(Link.MESSAGE_CONSTRAINTS);
        }
    }
}
