class HungryCat{
	public CatWithCatch eat(){
		return new CatWithCatch();
	}

	public boolean isFull(){
		return false;
	}
}

class CatWithCatch{
	public FullCat eat(){
		return new FullCat();
	}
	public boolean isFull(){
		return false;
	}
}

class FullCat{
	public boolean isFull(){
		return true;
	}
}