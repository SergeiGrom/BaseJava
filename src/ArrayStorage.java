import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int resumesNumber;

    void clear() {
        Arrays.fill(storage, 0, resumesNumber, null);
        resumesNumber = 0;
    }

    void save(Resume resume) {
        storage[resumesNumber] = resume;
        resumesNumber++;
    }

    Resume get(String uuid) {
        for (Resume resume : getAll()) {
            if (Objects.equals(resume.uuid, uuid)) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < resumesNumber; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, resumesNumber - 1 - i);
                storage[resumesNumber - 1] = null;
                resumesNumber--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, resumesNumber);
    }

    int size() {
        return resumesNumber;
    }
}
