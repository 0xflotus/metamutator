package metamutator;


public class ReturnReplacementOperatorMetaMutatorTest {
    @org.junit.Test
    public void testBinaryOperatorMetaMutator() throws java.lang.Exception {
        spoon.Launcher l = new spoon.Launcher();
        l.addInputResource("src/test/java/resources/Bar.java");
        l.addProcessor(new metamutator.ReturnReplacementOperatorMetaMutator());
        l.run();
        spoon.reflect.declaration.CtClass c = ((spoon.reflect.declaration.CtClass)(l.getFactory().Package().getRootPackage().getElements(new spoon.reflect.visitor.filter.NameFilter("Bar")).get(0)));
        java.lang.System.out.println("// Metaprogram: ");
        java.lang.System.out.println(c.toString());
        bsh.Interpreter bsh = new bsh.Interpreter();
        java.lang.Object o = ((java.lang.Class)(bsh.eval(c.toString()))).newInstance();
        metamutator.Selector sel = metamutator.Selector.getSelectorByName(((metamutator.ReturnReplacementOperatorMetaMutator.PREFIX) + "5"));
        sel.choose(0);
        org.junit.Assert.assertEquals(5, org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op_add", new java.lang.Object[]{ 2 , 3 }));
        sel.choose(1);
        org.junit.Assert.assertEquals(((java.lang.Integer.MIN_VALUE) + 1), org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op_add", new java.lang.Object[]{ 0 , 0 }));
        sel.choose(2);
        org.junit.Assert.assertEquals(((java.lang.Integer.MAX_VALUE) - 1), org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op_add", new java.lang.Object[]{ 0 , 0 }));
        sel.choose(3);
        org.junit.Assert.assertEquals(0, org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op_add", new java.lang.Object[]{ 20 , 5 }));
        sel = metamutator.Selector.getSelectorByName(((metamutator.ReturnReplacementOperatorMetaMutator.PREFIX) + "9"));
        sel.choose(0);
        org.junit.Assert.assertEquals("Hello World", org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op_get_s", new java.lang.Object[]{ "Hello World" }));
        sel.choose(1);
        org.junit.Assert.assertEquals(null, org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op_get_s", new java.lang.Object[]{ "Hello World" }));
    }
}

