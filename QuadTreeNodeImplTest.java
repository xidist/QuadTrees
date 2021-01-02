import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

public class QuadTreeNodeImplTest {
    
    
    @Test(expected = IllegalArgumentException.class)
    public void nullImage() {
        QuadTreeNodeImpl.buildFromIntArray(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emptyImage() {
        int[][] arr = null; //object created, but points to null, therefore empty
        QuadTreeNodeImpl.buildFromIntArray(arr);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void notSquareImage() {
        int[][] arr = new int[2][1];
        QuadTreeNodeImpl.buildFromIntArray(arr);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void notPowerOfTwo() {
        int[][] arr = new int[2][1];
        QuadTreeNodeImpl.buildFromIntArray(arr);
    }
    
    //test isPowerOfTwo helper function
    @Test
    public void isPowerOf22Test() {
        assertTrue(QuadTreeNodeImpl.isPowerOfTwo(2));
    }
    
    @Test
    public void isPowerOf2256() {
        assertTrue(QuadTreeNodeImpl.isPowerOfTwo(256));
    }
    
    @Test
    public void isPowerOf21() {
        assertTrue(QuadTreeNodeImpl.isPowerOfTwo(1));
    }
    
    
    
    private int[][] unitImage;
    private int[][] sameColor2by2;
    private int[][] diffColor2by2;
    private int[][] sameColor4by4;
    private int[][] tlDiff4by4;
    private int[][] tltlDiff4by4;
    private int[][] sameColor8by8;
    private int[][] trDiff8by8;
    private int[][] trDiff2by2;
    private int[][] trDiff4by4;
    
    private QuadTreeNodeImpl runitImage;
    private QuadTreeNodeImpl rsameColor2by2;
    private QuadTreeNodeImpl rdiffColor2by2;
    private QuadTreeNodeImpl rsameColor4by4;
    private QuadTreeNodeImpl rtlDiff4by4;
    private QuadTreeNodeImpl rtltlDiff4by4;
    private QuadTreeNodeImpl rsameColor8by8;
    private QuadTreeNodeImpl rtrDiff8by8;
    private QuadTreeNodeImpl rtrDiff2by2;
    private QuadTreeNodeImpl rtrDiff4by4;
    
    //create arrays (images)
    @Before
    public void setUpImages() {
        unitImage = new int[][] {
            {1}
        };
        
        runitImage = QuadTreeNodeImpl.buildFromIntArray(unitImage);
        
        sameColor2by2 = new int[][] {
            {1,1},
            {1,1}
        };
        
        rsameColor2by2 = QuadTreeNodeImpl.buildFromIntArray(sameColor2by2);
        
        diffColor2by2 = new int[][] {
            {1,2},
            {3,4}
        };
        
        rdiffColor2by2 = QuadTreeNodeImpl.buildFromIntArray(diffColor2by2);
        
        sameColor4by4 = new int[][] {
            {1,1,1,1},
            {1,1,1,1},
            {1,1,1,1},
            {1,1,1,1}
        };
        
        rsameColor4by4 = QuadTreeNodeImpl.buildFromIntArray(sameColor4by4);
        
        tlDiff4by4 = new int[][] {
            {-1,-1,1,1},
            {-1,-1,1,1},
            {1,1,1,1},
            {1,1,1,1}
        };
        
        rtlDiff4by4 = QuadTreeNodeImpl.buildFromIntArray(tlDiff4by4);
        
        tltlDiff4by4 = new int[][] {
            {0,-1,1,1},
            {-1,-1,1,1},
            {1,1,1,1},
            {1,1,1,1}
        };
        
        rtltlDiff4by4 = QuadTreeNodeImpl.buildFromIntArray(tltlDiff4by4);
        
               
        sameColor8by8 = new int[][] {
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1}
        };
        
        rsameColor8by8 = QuadTreeNodeImpl.buildFromIntArray(sameColor8by8);
        
        trDiff8by8 = new int[][] {
            {1,1,1,1,1,1,1,0},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1}
        };
        
        rtrDiff8by8 = QuadTreeNodeImpl.buildFromIntArray(trDiff8by8);
        
        trDiff2by2 = new int[][] {
            {1,0},
            {1,1}
        };
        
        rtrDiff2by2 = QuadTreeNodeImpl.buildFromIntArray(trDiff2by2);
        
        trDiff4by4 = new int[][] {
            {1,1,1,0},
            {1,1,1,1},
            {1,1,1,1},
            {1,1,1,1}
        };
        
        rtrDiff4by4 = QuadTreeNodeImpl.buildFromIntArray(trDiff4by4);
        
    } //end setup
    
  
    @Test
    public void unitImageTestsize() {
        int expected = 1;
        assertEquals(runitImage.getSize(), expected);
    }
    
    @Test
    public void unitImageTestisLeaf() {
        assertTrue(runitImage.isLeaf());
    }

    @Test
    public void unitImageTestdimension() {
        int expected = 1;
        assertEquals(runitImage.getDimension(), expected);
    }
    
    @Test
    public void unitImageTestdecompress() {
        assertArrayEquals(runitImage.decompress(), unitImage);
    }
    
    @Test
    public void unitImageTestcompressionRatio() {
        double expected = 1;
        assertEquals(runitImage.getCompressionRatio(), expected, 0.001);
    }
    

    @Test
    public void sameColor2by2TestisLeaf() {
        assertTrue(rsameColor2by2.isLeaf());
    }
    
    
    @Test
    public void sameColor2by2Testdimension() {
        int expected = 2;
        assertEquals(rsameColor2by2.getDimension(), expected);
    }
    
    @Test
    public void sameColor2by2Testdecompress() {
        assertArrayEquals(rsameColor2by2.decompress(), sameColor2by2);
    }
    
    @Test
    public void sameColor2by2TestcompressionRatio() {
        double expected = 0.25;
        assertEquals(rsameColor2by2.getCompressionRatio(), expected, 0.001);
    }

    @Test
    public void diffColor2by2Testsize() {
        int expected = 5;
        assertEquals(rdiffColor2by2.getSize(), expected);
    }
    
    @Test
    public void diffColor2by2TestisLeaf() {
        assertFalse(rdiffColor2by2.isLeaf());
    }
   
    
    @Test
    public void diffColor2by2Testdimension() {
        int expected = 2;
        assertEquals(rdiffColor2by2.getDimension(), expected);
    }
    
    @Test
    public void diffColor2by2Testdecompress() {
        assertArrayEquals(rdiffColor2by2.decompress(), diffColor2by2);
    }
    
    @Test
    public void diffColor2by2TestcompressionRatio() {
        double expected = 1.25;
        assertEquals(rdiffColor2by2.getCompressionRatio(), expected, 0.001);
    }

    @Test
    public void sameColor4by4Testsize() {
        int expected = 1;
        assertEquals(rsameColor4by4.getSize(), expected);
    }
    
    @Test
    public void sameColor4by4TestisLeaf() {
        assertTrue(rsameColor4by4.isLeaf());
    }
    
    
    @Test
    public void sameColor4by4Testdimension() {
        int expected = 4;
        assertEquals(rsameColor4by4.getDimension(), expected);
    }
    
    @Test
    public void sameColor4by4Testdecompress() {
        assertArrayEquals(rsameColor4by4.decompress(), sameColor4by4);
    }
    
    @Test
    public void sameColor4by4TestcompressionRatio() {
        double expected = 1.0 / 16.0;
        assertEquals(rsameColor4by4.getCompressionRatio(), expected, 0.001);
    }
 
    @Test
    public void tlDiff4by4Testsize() {
        int expected = 5;
        assertEquals(rtlDiff4by4.getSize(), expected);
    }
    
    @Test
    public void tlDiff4by4TestisLeaf() {
        assertFalse(rtlDiff4by4.isLeaf());
    }
    
    
    
    @Test
    public void tlDiff4by4Testdimension() {
        int expected = 4;
        assertEquals(rtlDiff4by4.getDimension(), expected);
    }
    
    @Test
    public void tlDiff4by4Testdecompress() {
        assertArrayEquals(rtlDiff4by4.decompress(), tlDiff4by4);
    }
    
    @Test
    public void tlDiff4by4TestcompressionRatio() {
        double expected = 5.0 / 16.0;
        assertEquals(rtlDiff4by4.getCompressionRatio(), expected, 0.001);
    }
    

    @Test
    public void brsameColor4by4() {
        
        assertArrayEquals(rtlDiff4by4.getQuadrant(QuadTreeNode.QuadName.BOTTOM_RIGHT)
                .decompress(), 
                rsameColor2by2.decompress());
    }
    
    @Test
    public void tlDiff4by4getColor() {
        int expected = -1;
        assertEquals(rtlDiff4by4.getColor(0,0), expected);
    }
    
    @Test
    public void tlDiff4by4getColorbr() {
        int expected = 1;
        assertEquals(rtlDiff4by4.getColor(3,3), expected);
    }
    
    @Test
    public void tlDiff4by4getColortlbr() {
        int expected = -1;
        assertEquals(rtlDiff4by4.getQuadrant(QuadTreeNode.QuadName.TOP_LEFT).
                                        getColor(0,0), expected);
    }
    
    @Test
    public void tltlDiff4by4getColor() {
        int expected = 0;
        assertEquals(rtltlDiff4by4.getColor(0,0), expected);
    }
    
    @Test
    public void tltlDiff4by4getColortlbr() {
        int expected = -1;
        assertEquals(rtltlDiff4by4.getQuadrant(QuadTreeNode.QuadName.TOP_LEFT).
                            getColor(1, 1), expected);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getColorOutOfBounds() {
        int expected = 0;
        assertEquals(rtltlDiff4by4.getColor(4,4), expected);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getColorOutOfBoundsleaf() {
        int expected = 1;
        assertEquals(runitImage.getColor(1,1), expected);
    }
    
    @Test
    public void unitImagegetColor() {
        int expected = 1;
        assertEquals(runitImage.getColor(0, 0), expected);
    }
    
    @Test
    public void unitImagesetColor() {
        runitImage.setColor(0, 0, 100);
        int[][] expected = {
                {100}
        };
        assertArrayEquals(runitImage.decompress(), expected);
        assertEquals(runitImage.getColor(0,0), 100);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void unitImagesetColoroutOfBounds() {
        runitImage.setColor(1, 0, 100);
        assertEquals(runitImage.getColor(1,0), 100);
    }
    
    
    
    @Test
    public void sameColor2by2setColor() {
        rsameColor2by2.setColor(0, 0, 100);
        int[][] expected = {
                {100,1},
                {1,1}
            };
        assertArrayEquals(rsameColor2by2.decompress(), expected);
        assertEquals(rsameColor2by2.getColor(0, 0), 100);
    }
    
    
    
    @Test
    public void trDiff2by2setColor() {
        rtrDiff2by2.setColor(1, 0, 1);
        int[][] expected = sameColor2by2;
        assertArrayEquals(rtrDiff2by2.decompress(), expected);
        assertEquals(rtrDiff2by2.getColor(1, 0), 1);
    }
    

    @Test
    public void trDiff4by4decompress() {
        assertArrayEquals(rtrDiff4by4.decompress(), trDiff4by4);
    }

    
    @Test
    public void trDiff4by4setColor() {
        rtrDiff4by4.setColor(3, 0, 1);
        int[][] expected = sameColor4by4;
        assertArrayEquals(rtrDiff4by4.decompress(), expected);
        assertEquals(rtrDiff4by4.getColor(3, 0), 1);
    }
    
    @Test
    public void sameColor8by8decompress() {
        assertArrayEquals(rsameColor8by8.decompress(), sameColor8by8);
        assertEquals(rsameColor8by8.getSize(), 1);
    }

     
    @Test
    public void sameColor2by2Testsize() {
        int expected = 1;
        assertEquals(rsameColor2by2.getSize(), expected);
    }
    
    @Test
    public void trDiff4by4size() {
        assertEquals(rtrDiff4by4.getSize(), 9);
    }
    
    @Test
    public void trDiff8by8size() {
        assertEquals(rtrDiff8by8.getSize(), 13);
    }
    
    
    
    @Test
    public void trDiff4by4setColorMerge() {
        rtrDiff4by4.setColor(3, 0, 1);
        assertEquals(rtrDiff4by4.getColor(3, 3), 1); //passed
        assertArrayEquals(rtrDiff4by4.decompress(), sameColor4by4); //passed
        assertEquals(rtrDiff4by4.getSize(), 1);  //tests merge step
        
    }
    
 
    @Test
    public void tltlDiff4by4setColor() {
        rtltlDiff4by4.setColor(0, 0, -1);
        assertArrayEquals(rtltlDiff4by4.decompress(), tlDiff4by4);
        assertEquals(rtltlDiff4by4.getSize(), 5); //merge step
    }
    
    
    
    @Test
    public void trDiff8by8setColor() {
        rtrDiff8by8.setColor(7, 0, 1);
        assertArrayEquals(rtrDiff8by8.decompress(), sameColor8by8);
        assertEquals(rtrDiff8by8.getColor(7, 0), 1); //the color is changed 
        assertEquals(rtrDiff8by8.getSize(), 1); //merge to one node
    }
    
    
    @Test
    public void getColortr() {
        assertEquals(rtrDiff8by8.getColor(7, 0), 0);
    }
    
    @Test
    public void getColorbl() {
        assertEquals(rtrDiff8by8.getColor(0, 7), 1);
    }
    
    @Test
    public void getQuadranttr() {
        assertArrayEquals(rtlDiff4by4.getQuadrant(
                QuadTreeNode.QuadName.TOP_RIGHT).decompress(), 
                sameColor2by2);
    }
    
    @Test
    public void getQuadrantbl() {
        assertArrayEquals(rtlDiff4by4.getQuadrant(
                QuadTreeNode.QuadName.BOTTOM_LEFT).decompress(), 
                sameColor2by2);
    }
    
    @Test
    public void getQuadrantbr() {
        assertArrayEquals(rtlDiff4by4.getQuadrant(
                QuadTreeNode.QuadName.BOTTOM_RIGHT).decompress(), 
                sameColor2by2);
    }
    
    ////set color tests/////////////////
    @Test
    public void setolorNoMakeChildrenbl() {
        rdiffColor2by2.setColor(0, 1, 100);
        assertEquals(rdiffColor2by2.getColor(0, 1), 100);
    }
    
    @Test
    public void setolorNoMakeChildrenbr() {
        rdiffColor2by2.setColor(1, 1, 100);
        assertEquals(rdiffColor2by2.getColor(1, 1), 100);
    }
    
    
    
    
    @Test
    public void sameColor8by8setColor() {
        int[][] expected = new int[][] {
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,10000}
        };
        rsameColor8by8.setColor(7, 7, 10000);
        assertEquals(rsameColor8by8.getColor(7, 7), 10000);
        assertArrayEquals(rsameColor8by8.decompress(), expected);
    }
    
    
    
    @Test
    public void sameColor4by4setColor() { //tr
        rsameColor4by4.setColor(3, 0, 10000);
        assertEquals(rsameColor4by4.getColor(3, 0), 10000);
    }
    
    
    @Test
    public void sameColor4by4setColorbl() {
        rsameColor4by4.setColor(0, 3, 10000);
        assertEquals(rsameColor4by4.getColor(0, 3), 10000);
    }
    
    
    @Test
    public void sameColor4by4setColorbr() {
        rsameColor4by4.setColor(3, 3, 10000);
        assertEquals(rsameColor4by4.getColor(3, 3), 10000);
    }
    
 
    
    
}
