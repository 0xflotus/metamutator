package metamutator;

import static org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bsh.Interpreter;
import spoon.Launcher;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.NamedElementFilter;

public class ReturnReplacementOperatorMetaMutatorTest {

	@Test
    public void testReturnReplacementOperatorMetaMutator() throws Exception {
        // build the model and apply the transformation
        Launcher l = new Launcher();
        l.addInputResource("src/test/java/resources/Bar.java");
        l.addProcessor(new ReturnReplacementOperatorMetaMutator());
        l.run();

        // now we get the code of Foo
        CtClass c = l.getFactory().Package().getRootPackage().getElements(new NamedElementFilter<>(CtClass.class, "Bar")).get(0);
        
        // printing the metaprogram
        System.out.println("// Metaprogram: ");
        System.out.println(c.toString());

        // we prepare an interpreter for the transformed code
        Interpreter bsh = new Interpreter();

        // creating a new instance of the class
        Object o = ((Class)bsh.eval(c.toString())).newInstance();        
      
        // test with the first
        Selector sel = Selector.getSelectorByName(ReturnReplacementOperatorMetaMutator.PREFIX + "4");
        
        // INIT
        sel.choose(0);
        assertEquals(5, invokeExactMethod(o, "op_add", new Object[] {2, 3}));

        // INT_MIN
        sel.choose(1);
        assertEquals(Integer.MIN_VALUE + 1, invokeExactMethod(o, "op_add", new Object[] {0, 0}));
        
        // INT_MAX
        sel.choose(2);
        assertEquals(Integer.MAX_VALUE - 1, invokeExactMethod(o, "op_add", new Object[] {0, 0}));
        
        // ZERO
        sel.choose(3);
        assertEquals(0, invokeExactMethod(o, "op_add", new Object[] {20, 5}));
        
        // test with the second
        sel = Selector.getSelectorByName(ReturnReplacementOperatorMetaMutator.PREFIX + "8");
        
        // INIT
        sel.choose(0);
        assertEquals("Hello World", invokeExactMethod(o, "op_get_s", new Object[] {"Hello World"}));
        
        // NULL
        sel.choose(1);
        assertEquals(null, invokeExactMethod(o, "op_get_s", new Object[] {"Hello World"}));

    }
}