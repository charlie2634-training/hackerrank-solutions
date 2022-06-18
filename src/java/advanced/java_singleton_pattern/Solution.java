package java.advanced.java_singleton_pattern;

class Singleton {
	public String str;
	private static Singleton instance;

	public static Singleton getSingleInstance() {
		if (instance == null)
			instance = new Singleton();
		return instance;
	}

	private Singleton() {

	}
}