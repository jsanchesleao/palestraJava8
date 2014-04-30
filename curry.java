import java.util.Map;
import java.util.HashMap;
import java.util.function.Supplier;
import java.util.function.Function;

class Currying{

	public static void main(String... args){

		Registry<Integer> reg = new Registry<>();

		Integer value = reg.put("first", 1)
				 .map( r -> r.put("second", 2) )
				 .map( r -> r.put("third", 3) )
				 .getOrElse("first", () -> 10 );

		System.out.println( value );

	}

	static class State<A,B>{
		private final A result;
		private final B newState;
		State(A a, B b){
			result = a;
			newState = b;
		}
		public A getResult(){ return result; }
		public B getObject(){ return newState; }

		<T> State<A, T> map( Function<B, T> mapFunction ){
			return new State<A,T>( result, mapFunction.apply(newState) );
		}

		<T> State<A, T> flatMap( Function<B, State<A,T>> mapFunction ){
			return mapFunction.apply( newState );
		}

	}

	static class VoidState<A> extends State<Void, A>{
		VoidState(A a){
			super(null, a);
		}
		public Void getResult(){ return null; }
	}

	static class Registry<T>{

		final Map<String, T> map = new HashMap<>();
		Registry(){}
		Registry( Map<String, T> map ){
			map.forEach( (key, value) -> {
				this.map.put(key, value);
			});
		}
		Registry( Map<String, T> map, String extraName, T extraValue){
			this(map);
			this.map.put( extraName, extraValue );
		}
		Registry( Map<String, T> map, String removed){
			this(map);
			this.map.remove(removed);
		}
		
		VoidState<Registry<T>> put(String name, T t){
			return new VoidState<Registry<T>>( new Registry<>(map, name, t) );
		}

		T getOrElse(String name, Supplier<T> supplier){
			if( map.get(name) == null ){
				map.put( name, supplier.get() );
			}
			return map.get(name);
		}

		State<T, Registry<T>> remove(String name){
			T removed = map.get(name);
			return new State<>(removed, new Registry<T>(map, name) );
		}

		public String toString(){
			return map.toString();
		}
		
	}
}