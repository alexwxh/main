package seedu.module.logic.parser.linkcommandparsers;

import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.linkcommands.AddLinkCommand;
import seedu.module.logic.parser.ArgumentMultimap;
import seedu.module.logic.parser.ParserUtil;
import seedu.module.logic.parser.exceptions.ParseException;
import seedu.module.model.module.Link;

import static seedu.module.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TITLE;

public class AddLinkCommandParser {
    public AddLinkCommand parse(ArgumentMultimap argMultimap) throws ParseException{
        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
        if (argMultimap.getValue(PREFIX_LINK).isPresent() && argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            String title = argMultimap.getValue(PREFIX_TITLE).get();
            String link = argMultimap.getValue(PREFIX_LINK).get();
            Link addedLink = new Link(title.trim(), link.trim());
            return new AddLinkCommand(index, addedLink);
        } else {
            throw new ParseException(Link.MESSAGE_CONSTRAINTS);
        }
    }
}
