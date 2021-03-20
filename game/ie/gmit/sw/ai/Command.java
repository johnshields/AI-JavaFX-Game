package ie.gmit.sw.ai;

/*
 * Use implementations of this functional interface to specify
 * how a computer controlled game character should behave.
 */

@FunctionalInterface
public interface Command {
	public void execute();
}