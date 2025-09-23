package util;

public class List<T> {
    private int length = 0;
    private Entity first = null;
    private Entity last = null;

    public List() {}
    public List(T[] firstData, int size) {
        for (int i = 0; i < size; i++)
            append(firstData[i]);
    }

    public int size() {
        return length;
    }

    public void append(T data) {
        Entity entity = new Entity();
        entity.data = data;
        entity.next = null;
        if (first == null) {
            first = entity;
            entity.prev = null;
        } else {
            entity.prev = last;
            last.next = entity;
        }
        last = entity;
        length++;
    }
    public T get(int index) {
        Entity entity = first;

        for (int i = 0; i < index; i++)
            entity = entity.next;

        return entity.data;
    }
    public void set(int index, T data) {
        Entity entity = first;

        for (int i = 0; i < index; i++)
            entity = entity.next;

        entity.data = data;
    }
    public boolean remove(T data) {
        Entity entity = first;

        for (int i = 0; i < length; i++) {
            if (entity.data.equals(data)) {
                Entity prevEntiry = entity.prev;
                Entity nextEntity = entity.next;

                if (nextEntity != null)
                    nextEntity.prev = prevEntiry;
                else
                    last = prevEntiry;
                if (prevEntiry != null)
                    prevEntiry.next = nextEntity;
                else
                    first = nextEntity;

                length--;
                return true;
            }

            entity = entity.next;
        }
        return false;
    }
    public boolean contain(T data) {
        Entity entity = first;

        for (int i = 0; i < length; i++) {
            if (entity.data.equals(data))
                return true;

            entity = entity.next;
        }

        return false;
    }

    @java.lang.Override
    public java.lang.String toString() {
        Entity entity = first;
        String out = "";
        for (int i = 0; i < length; i++) {
            out = out + (i + 1) + ". " + entity.data.toString() + "\n";
            entity = entity.next;
        }

        return out;
    }

    private class Entity {
        public T data;
        public Entity next;
        public Entity prev;
    }
}