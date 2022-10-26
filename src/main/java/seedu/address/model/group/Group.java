package seedu.address.model.group;

import seedu.address.model.item.AbstractContainerItem;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.item.EntryType;
import seedu.address.model.person.Name;
import seedu.address.model.task.Task;

/**
 * Represents a Group in the address book.
 */
public class Group extends AbstractContainerItem {

    public static final String VALIDATION_REGEX = "[a-zA-Z][a-zA-Z0-9_-]*";
    public static final String MESSAGE_CONSTRAINTS = "A group name should only consist "
            + "of alphanumeric characters, underscores and hyphens only.\n";

    public Group(Name groupName) {
        this(groupName, null);
    }

    public Group(Name groupName, Group parent) {
        super(groupName, parent);
    }

    /**
     * Checks if the group name is valid. A group name is valid
     * if the group name is fully alphanumeric.
     *
     * @param groupName for a specific team.
     * @return true if the group name is valid, false otherwise.
     */
    public static boolean isValidGroupName(Name groupName) {
        return groupName.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if a task exists in this group
     *
     * @param task The task to check if exists
     * @return true if it exists in this Group, false otherwise
     */
    public boolean hasTask(Task task) {
        return contains(task);
    }

    /**
     * Adds a task to this group. The task must not already exist in this group.
     *
     * @param task The task to add
     */
    public void addTask(Task task) {
        add(task);
    }

    /**
     * Removes a task from this group. The task must already exist in this group
     *
     * @param task The task to remove
     */
    public void removeTask(Task task) {
        remove(task);
    }

    @Override
    public EntryType getEntryType() {
        return EntryType.TEAM;
    }

    @Override
    public boolean stronglyEqual(DisplayItem o) {
        if (!weaklyEqual(o)) {
            return false;
        }
        Group g = (Group) o;
        if (parent != null) {
            return parent.equals(g.parent);
        }
        return g.parent == null;
    }

    @Override
    public boolean weaklyEqual(DisplayItem o) {
        if (!(o instanceof Group)) {
            return false;
        }
        return ((Group) o).name.equals(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Group)) {
            return false;
        }
        return stronglyEqual((Group) obj);
    }

    @Override
    public boolean isPartOfContext(DisplayItem o) {
        if (o == null) {
            return true;
        }

        AbstractContainerItem tmp = parent;
        while (tmp != null) {
            if (tmp.equals(o)) {
                return true;
            }
            tmp = tmp.getParent();
        }
        return false;
    }

    @Override
    public Name getName() {
        return
    }
}
