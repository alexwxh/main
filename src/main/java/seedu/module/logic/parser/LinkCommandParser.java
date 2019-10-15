package seedu.module.logic.parser;

import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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

        try {
            String[] splitLink = trimmedArgs.split("/l");
            if(splitLink.length==1){
                throw new ParseException("Input format error. /l not found");
            }
            String[] nameArgs = splitLink[0].split("\\s+", 3);
            String action = nameArgs[0];
            if(action.equals("add")) {  //link add 1 title /l link
                if (Link.isValidUrl(splitLink[1].trim())) {
                    String title = nameArgs[2];
                    Link addedLink = new Link(title.trim(), splitLink[1].trim());
                    Index index = Index.fromOneBased(Integer.parseInt(nameArgs[1].trim()));
                    return new LinkCommand(action, index, addedLink);
                } else {
                    throw new ParseException(Link.MESSAGE_CONSTRAINTS);
                }
            }else if(action.equals("go")){
                Index index = Index.fromOneBased(Integer.parseInt(nameArgs[1].trim()));
                String linkTitle = splitLink[1].trim();
                return new LinkCommand(action, index, linkTitle);
            }else {
                throw new ParseException("Command not recognized");
            }
        }catch (ParseException e){
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkCommand.MESSAGE_USAGE), e);
        }
    }

}

