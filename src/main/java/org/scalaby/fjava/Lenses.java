package org.scalaby.fjava;

import java.util.function.Function;

/**
 * User: remeniuk
 */
public class Lenses {

    public static <A, B> Lens<A, B> lens(Function<A, B> get, Function2<A, B, A> set){
        return new Lens<A, B>(get, set);
    }

}
