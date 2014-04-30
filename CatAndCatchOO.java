class Cat{
	private Bird catch;
	private boolean full;

	public Cat(Bird catch){
		this.catch = null;
		this.full = false;
	}

	public void catchABird(Bird bird){
		this.catch = catch;
	}

	public void eat(){
		if( this.catch != null && !this.full ){
			this.catch = null;
			this.full = true;
		}
	}

	public boolean isFull(){
		return full;
	}
}