public class Main {

    public static void main(String[] args) {
	Automata auto = new Automata();

	/*
	 * auto.condition.add("0"); auto.condition.add("1"); auto.condition.add("2");
	 * auto.condition.add("3"); auto.condition.add("4"); auto.condition.add("5");
	 * auto.condition.add("6");
	 * 
	 * auto.zeroCondition = "0"; auto.finalConditions.add("5");
	 * 
	 * auto.fStartConditions.add("0"); auto.fStartConditions.add("0");
	 * auto.fStartConditions.add("2"); auto.fStartConditions.add("4");
	 * auto.fStartConditions.add("3"); auto.fStartConditions.add("1");
	 * 
	 * auto.fEndConditions.add("1"); auto.fEndConditions.add("2");
	 * auto.fEndConditions.add("4"); auto.fEndConditions.add("5");
	 * auto.fEndConditions.add("5"); auto.fEndConditions.add("6");
	 */

	/*
	 * auto.fStartConditions.add("1"); auto.fStartConditions.add("1");
	 * auto.fStartConditions.add("3"); auto.fStartConditions.add("5");
	 * auto.fStartConditions.add("5");
	 * 
	 * auto.fEndConditions.add("2"); auto.fEndConditions.add("4");
	 * auto.fEndConditions.add("2"); auto.fEndConditions.add("2");
	 * auto.fEndConditions.add("4");
	 */

	/*
	 * auto.graphCreator(); auto.unatainableCheck(); auto.deadendCheck();
	 * auto.show();
	 */

	auto.get_path();
	auto.read();
	auto.graphCreator();
	auto.unatainableCheck();
	auto.deadendCheck();
	auto.show();
    }
}
