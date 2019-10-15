package seedu.module.logic.parser;

import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.parser.CliSyntax.PREFIX_ACTION;
import static seedu.module.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.module.logic.commands.LinkCommand;
import seedu.module.commons.core.index.Index;
import seedu.module.logic.parser.exceptions.ParseException;
import seedu.module.model.module.Link;


/**
 * Parses input arguments and creates a new LinkCommand object
 */
public class LinkCommandParser implements Parser<LinkCommand>{

    /**
     * Parses the given {@code String} of arguments in the context of the LinkCommand
     * and returns a LinkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LinkCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ACTION, PREFIX_LINK, PREFIX_TITLE);
        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
        try {
            if(!argMultimap.getValue(PREFIX_ACTION).isPresent()){
                throw new ParseException("Input format error. a/ not found");
            }
            if(argMultimap.getValue(PREFIX_ACTION).get().equals("add")) {  //link add 1 title /l link
                if (argMultimap.getValue(PREFIX_LINK).isPresent() && argMultimap.getValue(PREFIX_TITLE).isPresent()) {
                    String title = argMultimap.getValue(PREFIX_TITLE).get();
                    String link = argMultimap.getValue(PREFIX_LINK).get();
                    Link addedLink = new Link(title.trim(), link.trim());
                    return new LinkCommand("add", index, addedLink);
                } else {
                    throw new ParseException(Link.MESSAGE_CONSTRAINTS);
                }
            }else if(argMultimap.getValue(PREFIX_ACTION).get().equals("go")){
                if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
                    String title = argMultimap.getValue(PREFIX_TITLE).get();
                    return new LinkCommand("go", index, title);
                }else{
                    throw new ParseException(Link.MESSAGE_CONSTRAINTS);
                }
            }else {
                throw new ParseException("Command not recognized");
            }
        }catch (ParseException e){
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkCommand.MESSAGE_USAGE), e);
        }
    }

}

