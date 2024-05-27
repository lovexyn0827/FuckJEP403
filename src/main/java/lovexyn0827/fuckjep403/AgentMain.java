package lovexyn0827.fuckjep403;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.AccessibleObject;

public final class AgentMain {
	public static void agentmain(String agentArgs, Instrumentation inst) {
		premain(agentArgs, inst);
	}
	
	public static void premain(String agentArgs, Instrumentation inst) {
		Jep403Fucker fuck = new Jep403Fucker();
		inst.addTransformer(fuck, true);
		try {
			System.out.println("Fuck -1");
			AccessibleObject.class.getFields();
			inst.retransformClasses(AccessibleObject.class);
		} catch (UnmodifiableClassException e) {
			System.out.println("Fuck Failed");
			e.printStackTrace();
		}

		inst.removeTransformer(fuck);
		System.out.println("Fuck End");
	}
}
