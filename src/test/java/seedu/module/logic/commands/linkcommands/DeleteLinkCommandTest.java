package seedu.module.logic.commands.linkcommands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.module.logic.commands.CommandResult;
import seedu.module.model.Model;
import seedu.module.model.ModelManager;
import seedu.module.model.ModuleBook;
import seedu.module.model.module.ArchivedModule;
import seedu.module.model.module.Link;
import seedu.module.model.module.TrackedModule;
import seedu.module.testutil.ArchivedModuleBuilder;
import seedu.module.testutil.ArchivedModuleListBuilder;
import seedu.module.testutil.ModuleBookBuilder;
import seedu.module.testutil.TrackedModuleBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.module.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.module.logic.commands.CommandTestUtil.assertCommandFailure;

class DeleteLinkCommandTest {
    private final String name = "website";
    private DeleteLinkCommand command = new DeleteLinkCommand(name);
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @BeforeEach
    public void beforeEach() {
        model = new ModelManager();
        expectedModel = new ModelManager();
        Link link = new Link ("website", "http://example.com");
        ArchivedModule archivedModule = new ArchivedModuleBuilder().build();
        ModuleBook moduleBook = new ModuleBookBuilder().withArchivedModules(
                new ArchivedModuleListBuilder().withArchivedModule(archivedModule).build())
                .build();
        model.setModuleBook(moduleBook);
        expectedModel.setModuleBook(moduleBook);
        TrackedModule trackedModule = new TrackedModuleBuilder()
                .withLinks(new ArrayList<>(Arrays.asList(link))).build();
        model.addModule(trackedModule);
        model.setDisplayedModule(trackedModule);
    }

    @Test
    void execute_deleteValidLink_success() {
        TrackedModule expectedModule = new TrackedModuleBuilder().build();
        expectedModel.addModule(expectedModule);
        expectedModel.setDisplayedModule(expectedModule);
        CommandResult expectedResult = new CommandResult(LinkCommand.MESSAGE_DELETE_SUCCESS);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_linkNotFound_throwsCommandException() {
        DeleteLinkCommand wrongCommand = new DeleteLinkCommand("wrongTitle");
        assertCommandFailure(wrongCommand, model, DeleteLinkCommand.MESSAGE_LINK_NOT_FOUND);
    }
}