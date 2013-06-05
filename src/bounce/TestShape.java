package bounce;

import java.awt.Color;

import junit.framework.TestCase;

/**
 * Test cases to test some subclasses of Shape.
 * 
 * @author Ian Warren, Xuzong Chen
 */
public class TestShape extends TestCase {
	private MockPainter painter;

	public void setUp() {
		painter = new MockPainter();
	}

	public void testDynaRecOffBR() {
		DynamicRectangleShape dr = new DynamicRectangleShape(80, 80, 23, 23, 10, 10,
				new Color(122, 42, 213));
		dr.paintShape(painter);
		dr.move(100, 100);
		dr.paintShape(painter);
		assertEquals(
				"(filled-rectangle 80,80,10,10,java.awt.Color[r=122,g=42,b=213])(filled-rectangle 90,90,10,10,java.awt.Color[r=122,g=42,b=213])",
				painter.toString());
	}

	public void testDynaRecOffBL() {
		DynamicRectangleShape dr = new DynamicRectangleShape(10, 80, -23, 23, 10, 10,
				new Color(2, 3, 1));
		dr.paintShape(painter);
		dr.move(100, 100);
		dr.paintShape(painter);
		assertEquals(
				"(filled-rectangle 10,80,10,10,java.awt.Color[r=2,g=3,b=1])(filled-rectangle 0,90,10,10,java.awt.Color[r=2,g=3,b=1])",
				painter.toString());
	}

	public void testDynaRecOffTL() {
		DynamicRectangleShape dr = new DynamicRectangleShape(10, 10, -23, -23, 10, 10,
				new Color(2, 3, 1));
		dr.paintShape(painter);
		dr.move(100, 100);
		dr.paintShape(painter);
		assertEquals(
				"(filled-rectangle 10,10,10,10,java.awt.Color[r=2,g=3,b=1])(filled-rectangle 0,0,10,10,java.awt.Color[r=2,g=3,b=1])",
				painter.toString());

	}

	public void testDynaRecOffTR() {
		DynamicRectangleShape dr = new DynamicRectangleShape(80, 10, 23, -23, 10, 10,
				new Color(2, 3, 1));
		dr.paintShape(painter);
		dr.move(100, 100);
		dr.paintShape(painter);
		assertEquals(
				"(filled-rectangle 80,10,10,10,java.awt.Color[r=2,g=3,b=1])(filled-rectangle 90,0,10,10,java.awt.Color[r=2,g=3,b=1])",
				painter.toString());
	}

	public void testDynaRecOffT() {
		DynamicRectangleShape dr = new DynamicRectangleShape(45, 10, 0, -23, 10, 10,
				new Color(2, 3, 1));
		dr.paintShape(painter);
		dr.move(100, 100);
		dr.paintShape(painter);
		assertEquals(
				"(filled-rectangle 45,10,10,10,java.awt.Color[r=2,g=3,b=1])(rectangle 45,0,10,10)",
				painter.toString());
	}

	public void testDynaRecOffB() {
		DynamicRectangleShape dr = new DynamicRectangleShape(45, 80, 0, 23, 10, 10,
				new Color(2, 3, 1));
		dr.paintShape(painter);
		dr.move(100, 100);
		dr.paintShape(painter);
		assertEquals(
				"(filled-rectangle 45,80,10,10,java.awt.Color[r=2,g=3,b=1])(rectangle 45,90,10,10)",
				painter.toString());
	}

	public void testDynaRecOffL() {
		DynamicRectangleShape dr = new DynamicRectangleShape(10, 45, -29, 0, 10, 10,
				new Color(2, 3, 1));
		dr.paintShape(painter);
		dr.move(100, 100);
		dr.paintShape(painter);
		assertEquals(
				"(filled-rectangle 10,45,10,10,java.awt.Color[r=2,g=3,b=1])(filled-rectangle 0,45,10,10,java.awt.Color[r=2,g=3,b=1])",
				painter.toString());
	}

	public void testDynaRecOffR() {
		DynamicRectangleShape dr = new DynamicRectangleShape(80, 45, 29, 0, 10, 10,
				new Color(2, 3, 1));
		dr.paintShape(painter);
		dr.move(100, 100);
		dr.paintShape(painter);
		assertEquals(
				"(filled-rectangle 80,45,10,10,java.awt.Color[r=2,g=3,b=1])(filled-rectangle 90,45,10,10,java.awt.Color[r=2,g=3,b=1])",
				painter.toString());
	}

	public void testDynaRecSimple() {
		DynamicRectangleShape dynamicRectangleShape = new DynamicRectangleShape();
		dynamicRectangleShape.paintShape(painter);
		dynamicRectangleShape.move(500, 500);
		dynamicRectangleShape.paintShape(painter);
		assertEquals("(rectangle 0,0,25,35)(rectangle 5,5,25,35)",
				painter.toString());
	}

	public void testGemBig() {
		GemShape gemShape = new GemShape(100, 20, 12, 15, 40, 90);
		gemShape.paintShape(painter);
		gemShape.move(500, 500);
		gemShape.paintShape(painter);
		assertEquals("(line 100,65,120,20)" + "(line 120,20,120,20)"
				+ "(line 120,20,140,65)" + "(line 140,65,120,110)"
				+ "(line 120,110,120,110)" + "(line 120,110,100,65)"
				+ "(line 112,80,132,35)" + "(line 132,35,132,35)"
				+ "(line 132,35,152,80)" + "(line 152,80,132,125)"
				+ "(line 132,125,132,125)" + "(line 132,125,112,80)",
				painter.toString());
	}

	public void testGemSmall() {
		GemShape gemShape = new GemShape(100, 20, 12, 15);
		gemShape.paintShape(painter);
		gemShape.move(500, 500);
		gemShape.paintShape(painter);
		assertEquals("(line 100,37,112,20)" + "(line 112,20,112,20)"
				+ "(line 112,20,125,37)" + "(line 125,37,112,55)"
				+ "(line 112,55,112,55)" + "(line 112,55,100,37)"
				+ "(line 112,52,124,35)" + "(line 124,35,124,35)"
				+ "(line 124,35,137,52)" + "(line 137,52,124,70)"
				+ "(line 124,70,124,70)" + "(line 124,70,112,52)",
				painter.toString());
	}

	public void testOvalSimple() {
		OvalShape ovalShape = new OvalShape(100, 20, 12, 15);
		ovalShape.paintShape(painter);
		ovalShape.move(500, 500);
		ovalShape.paintShape(painter);
		assertEquals("(oval 100,20,25,35)" + "(oval 112,35,25,35)",
				painter.toString());
	}

	public void testOvalOffBR() {
		OvalShape ovalShape = new OvalShape(10, 90, -12, 15);
		ovalShape.paintShape(painter);
		ovalShape.move(125, 135);
		ovalShape.paintShape(painter);
		ovalShape.move(125, 135);
		ovalShape.paintShape(painter);
		assertEquals("(oval 10,90,25,35)" + "(oval 0,100,25,35)"
				+ "(oval 12,85,25,35)", painter.toString());
	}

	public void testOvalOffL() {
		OvalShape shape = new OvalShape(10, 20, -12, 15);
		shape.paintShape(painter);
		shape.move(10000, 10000);
		shape.paintShape(painter);
		shape.move(10000, 10000);
		shape.paintShape(painter);
		assertEquals("(oval 10,20,25,35)(oval 0,35,25,35)"
				+ "(oval 12,50,25,35)", painter.toString());
	}

	public void testRecOffBR() {
		RectangleShape rectangleShape = new RectangleShape(10, 90, -12, 15);
		rectangleShape.paintShape(painter);
		rectangleShape.move(125, 135);
		rectangleShape.paintShape(painter);
		rectangleShape.move(125, 135);
		rectangleShape.paintShape(painter);
		assertEquals("(rectangle 10,90,25,35)(rectangle 0,100,25,35)"
				+ "(rectangle 12,85,25,35)", painter.toString());
	}

	public void testOvalOffR() {
		OvalShape ovalShape = new OvalShape(100, 20, 12, 15);
		ovalShape.paintShape(painter);
		ovalShape.move(135, 10000);
		ovalShape.paintShape(painter);
		ovalShape.move(135, 10000);
		ovalShape.paintShape(painter);
		assertEquals("(oval 100,20,25,35)(oval 110,35,25,35)"
				+ "(oval 98,50,25,35)", painter.toString());
	}

	public void testRecOffL() {
		RectangleShape rectangleShape = new RectangleShape(10, 20, -12, 15);
		rectangleShape.paintShape(painter);
		rectangleShape.move(10000, 10000);
		rectangleShape.paintShape(painter);
		rectangleShape.move(10000, 10000);
		rectangleShape.paintShape(painter);
		assertEquals("(rectangle 10,20,25,35)(rectangle 0,35,25,35)"
				+ "(rectangle 12,50,25,35)", painter.toString());

	}

	public void testRecOffR() {
		RectangleShape rectangleShape = new RectangleShape(100, 20, 12, 15);
		rectangleShape.paintShape(painter);
		rectangleShape.move(135, 10000);
		rectangleShape.paintShape(painter);
		rectangleShape.move(135, 10000);
		rectangleShape.paintShape(painter);
		assertEquals("(rectangle 100,20,25,35)(rectangle 110,35,25,35)"
				+ "(rectangle 98,50,25,35)", painter.toString());

	}

	public void testRecSimple() {
		RectangleShape rectangleShape = new RectangleShape(100, 20, 12, 15, 25,
				35);
		rectangleShape.paintShape(painter);
		rectangleShape.move(500, 500);
		rectangleShape.paintShape(painter);
		assertEquals("(rectangle 100,20,25,35)(rectangle 112,35,25,35)",
				painter.toString());
	}

	public void testNestingShape() {
		NestingShape nestingShape = new NestingShape(20, 5, 2, 7, 70, 15,
				"caption");
		OvalShape oval = new OvalShape();
		nestingShape.add(oval);
		nestingShape.paint(painter);
		nestingShape.move(500, 500);
		assertEquals(
				"(rectangle 20,5,70,15)(translate (20,5))(oval 0,0,25,35)(text \"null\"  which should be centred at 12,17)(translate (-20,-5))(text \"caption\"  which should be centred at 55,12)",
				painter.toString());

		NestingShape inner = new NestingShape(5, 5, 2, 2, 30, 30, "inner");
		oval = new OvalShape();
		inner.add(oval);
		nestingShape.add(inner);
		nestingShape.paint(painter);
		nestingShape.move(500, 500);
		assertEquals(
				"(rectangle 20,5,70,15)(translate (20,5))(oval 0,0,25,35)(text \"null\"  which should be centred at 12,17)(translate (-20,-5))(text \"caption\"  which should be centred at 55,12)(rectangle 22,12,70,15)(translate (22,12))(oval 5,-20,25,35)(text \"null\"  which should be centred at 17,-3)(rectangle 5,5,30,30)(translate (5,5))(oval 0,0,25,35)(text \"null\"  which should be centred at 12,17)(translate (-5,-5))(text \"inner\"  which should be centred at 20,20)(translate (-22,-12))(text \"caption\"  which should be centred at 57,19)",
				painter.toString());
	}
}
