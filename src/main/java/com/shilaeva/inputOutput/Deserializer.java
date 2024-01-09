package com.shilaeva.inputOutput;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class Deserializer {
    public static Animal[] deserializeAnimalArray(byte[] data) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            int size = ois.readInt();
            Animal[] animals = new Animal[size];

            for (int i = 0; i < size; ++i) {
                animals[i] = (Animal) ois.readObject();
            }

            return animals;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
