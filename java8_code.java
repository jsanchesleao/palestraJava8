import java.util.List;
import java.util.Arrays;
import java.util.function.Consumer;

class UsingInnerClasses{


  public static void main(String... args){

    List<String> strings = Arrays.asList("java", "perl", "clojure", "js");

    strings.forEach( new Consumer<String>(){
      public void accept(String string){
        System.out.println(string);
      }
    });

  }


}

class UsingLambdas{

  public static void main(String... args){
    List<String> strings = Arrays.asList("java", "perl", "clojure", "js");

    strings.forEach( string -> System.out.println(string) );
  }
}


class UsingStreams{
  public static void main(String... args){
    List<String> strings = Arrays.asList("java", "perl", "clojure", "js");

    strings.stream()
           .filter( string -> string.startsWith("j") )
           .map( String::toUpperCase )
           .forEach( System.out::println );
  }
}