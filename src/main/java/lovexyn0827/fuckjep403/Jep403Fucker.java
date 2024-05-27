package lovexyn0827.fuckjep403;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public final class Jep403Fucker implements ClassFileTransformer {
	public final byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, 
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		if (!className.contains("AccessibleObject")) {
			return null;
		}

		System.err.println("Fuck 0");
		ClassReader cr = new ClassReader(classfileBuffer);
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		ClassTransformer ct = new ClassTransformer(cw);
		cr.accept(ct, 0);
		return cw.toByteArray();
	}
	
	private static final class ClassTransformer extends ClassVisitor {
		protected ClassTransformer(ClassVisitor classVisitor) {
			super(Opcodes.ASM9, classVisitor);
		}
		
		public MethodVisitor visitMethod(int access, String name, String descriptor, 
				String signature, String[] exceptions) {
			MethodVisitor mvNext = super.visitMethod(access, name, descriptor, signature, exceptions);
			System.out.println("Fuck 1");
			if (!"checkCanSetAccessible".equals(name) 
					|| !"(Ljava/lang/Class;Ljava/lang/Class;Z)Z".equals(descriptor)) {
				return mvNext;
			}
			
			System.out.println("Fuck 2");
			return new MethodVisitor(Opcodes.ASM9, mvNext) {
				public void visitCode() {
					super.visitCode();
					System.out.println("Fuck 3");
					this.visitInsn(Opcodes.ICONST_1);
					Label dead = new Label();
					this.visitJumpInsn(Opcodes.IFEQ, dead);
					this.visitInsn(Opcodes.ICONST_1);
					this.visitInsn(Opcodes.IRETURN);
					this.visitLabel(dead);
				}
			};
		}
	}
}
