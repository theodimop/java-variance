package org.example;


import java.util.ArrayList;
import java.util.List;

/**
 * Confusion: With Variance we look with what we can substitute a type (1). And (2) what we can do with that
 * type. 1 and 2 are related so you can only understand 2 if you have 1 in context.
 *
 *
 * (1) Variance Types
 * ------------------
 * Co-variant       ? extends Dog   -> Allows Type substitution with subclass (Iggy substitutes Dog)
 * In-variant                       -> Does not allow Substitution List<Dogs> cannot substitute List<Animal>
 * Contra-variant   ? super Animal  -> Allows Type substitution with superclass (Animal substitutes Dog)
 *
 */
public class Variance {
    public interface B {}
    public interface Animal {}
    public interface Cat extends Animal {}
    public interface Dog extends Animal, B {}
    public interface Iggy extends Dog {}

    public interface Pug extends Dog {}

    public static void main(String[] args) {
        List<B> b = new ArrayList<>();
        List<Animal> animals = new ArrayList<>();
        List<Dog>  dogs = new ArrayList<>();
        List<Cat>  cats = new ArrayList<>();
        List<Iggy> iggies = new ArrayList<>();
        /*
        List<? extends Dog> l -> You can pass only the same or more specific list. List<Iggy|Pug>,
        List<? super Dog> l -> You can pass only the same or more general list. List<Animal|Object>
        */

        variance(animals, animals);
        variance(animals, dogs);
        variance(animals, cats);
        variance(animals, iggies);

        variance(dogs, animals);
        variance(dogs, dogs);
        variance(dogs, cats);
        variance(dogs, iggies);

        variance(cats, animals);
        variance(cats, dogs);
        variance(cats, cats);
        variance(cats, iggies);

        variance(iggies, b);
        variance(iggies, b);
        variance(iggies, animals);
        variance(iggies, dogs);
        variance(iggies, cats);
        variance(iggies, iggies);
    }

    /**
     *
     * @param dogExt You can READ DOG or any if its SuperClass (Animal, Object)
     *               List contains any subclass of Dog, (Iggy, Pug).
     *               You will use this syntax to be able to pass a List of any Dog breed,
     *               READ: The only info you have is that you will retrieve a dog from this list, but you have no idea about what breed.
     *               WRITE: You know that the list contains a single subtype of Dog (breed) but no clue which so you cannot add to this list
     *
     * @param dogSpr TL:DR: You can WRITE DOG or any of its SUB CLASS (IGGY,PUB)
     *               List contains any superclass of Dog (Animal, B).
     *               You will use this syntax to be able to pass a List of any SuperClass of dog
     *               READ: You know the list contains one of the superclasses of Dog (Could be Iggy, Pug) but you do not know which
     *               Why can't you read a Dog?
     *               The type List<? super Dog> could potentially be a list of Object or any superclass of Dog, so the compiler prevents you from assuming that the list only contains Dog instances.
     *               If you were allowed to read a Dog from this list, it could lead to runtime errors if the actual list is of a superclass type that contains other objects.
     *               WRITE: You know that list contains Dogs or sth more generic so you can write any subclass of Dog (Iggy, Pub)
     *
     */
    static void variance(List<? extends Dog> dogExt, List<? super Dog> dogSpr) {
        Animal animal = dogExt.get(0);
        Dog dog = dogExt.get(0);
        Cat cat = dogExt.get(0);
        Iggy iggy = dogExt.get(0);
        dogExt.add(new Animal(){});
        dogExt.add(new Dog(){});
        dogExt.add(new Cat(){});
        dogExt.add(new Iggy(){});


        Animal animal1 = dogSpr.get(0);
        Dog dog1 = dogSpr.get(0);
        Cat cat1 = dogSpr.get(0);
        Iggy iggy1 = dogSpr.get(0);

        dogSpr.add(new Animal(){});
        dogSpr.add(new Dog(){});
        dogSpr.add(new Cat(){});
        dogSpr.add(new Iggy(){});

    }
}