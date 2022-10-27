package seedu.address.logic.commands.teams;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.AbstractSingleItem;
import seedu.address.model.person.Person;

/**
 * Removes a user from the current context
 */
public class RemoveUserFromTeamCommand extends TeamCommand {
    public static final String SUBCOMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SUBCOMMAND_WORD
            + ": Removes the user specified by the index from the current team when in the team context\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + SUBCOMMAND_WORD + " 1\n";

    public static final String REMOVAL_SUCCESS = " User %s has been removed from %s%n";
    public static final String INVALID_INPUT = "This method takes in only Person types!";
    
    private Person toRemove = null;
    private final Index targetIndex;

    public RemoveUserFromTeamCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public void setInput(Object additionalData) throws CommandException {
        if (additionalData == null || !(additionalData instanceof Person)) {
            toRemove = null;
            return;
        }
        toRemove = (Person) additionalData;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        AbstractSingleItem currContext = model.getContextContainer();
        if (currContext == null) {
            return new CommandResult("You are not in any team scope right now!");
        }

        if (toRemove == null) {
            List<Person> lastShownList = model.getFilteredPersonList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            toRemove = lastShownList.get(targetIndex.getZeroBased());
        }

        toRemove.removeParent(currContext);
        model.updateContextContainer(currContext);
        return new CommandResult(String.format(REMOVAL_SUCCESS, toRemove, currContext));
    }
}
