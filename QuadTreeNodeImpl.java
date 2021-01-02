
// CIS 121, QuadTree

public class QuadTreeNodeImpl implements QuadTreeNode {

    private Integer color;
    private int dimension;
    private boolean isLeaf;

    private QuadTreeNodeImpl tlChild;
    private QuadTreeNodeImpl trChild;
    private QuadTreeNodeImpl blChild;
    private QuadTreeNodeImpl brChild;

    /**
     * ! Do not delete this method ! Please implement your logic inside this method
     * without modifying the signature of this method, or else your code won't
     * compile.
     * <p/>
     * As always, if you want to create another method, make sure it is not public.
     *
     * @param image image to put into the tree
     * @return the newly build QuadTreeNode instance which stores the compressed
     *         image
     * @throws IllegalArgumentException if image is null
     * @throws IllegalArgumentException if image is empty
     * @throws IllegalArgumentException if image.length is not a power of 2
     * @throws IllegalArgumentException if image, the 2d-array, is not a perfect
     *                                  square
     */
    public static QuadTreeNodeImpl buildFromIntArray(int[][] image) {

        // make sure that image is valid

        // check if image is null
        if (image == null) {
            throw new IllegalArgumentException();
        }

        // check if image is empty
        if (image.length == 0) {
            throw new IllegalArgumentException();
        }

        // check that height is a power of 2, and every row is aswell
        // check if perfect square --- aka height = length
        boolean isSquare = true;
        boolean powerOf2 = isPowerOfTwo(image.length);

        for (int r = 0; r < image.length; r++) {
            if (image[r].length != image.length) {
                isSquare = false;
            }
            if (!isPowerOfTwo(image[r].length)) {
                powerOf2 = false;
            }
        }
        if (!isSquare || !powerOf2) {
            throw new IllegalArgumentException();
        }

        /////////////////////// begin////////////////////////

        // create tree recursively

        QuadTreeNodeImpl r = new QuadTreeNodeImpl(image, 0, 0, image.length);

        return r; // returns root of whole tree

    }

    private QuadTreeNodeImpl(int c, int dim, boolean l) {
        color = c;
        dimension = dim;
        isLeaf = l;
    }

    // recursive constructor, creates tree
    private QuadTreeNodeImpl(int[][] image, int y, int x, int dim) {

        // create node
        this.dimension = dim;
        this.color = null;

        if (dim > 1) {
            // create children through rec call
            this.tlChild = new QuadTreeNodeImpl(image, y, x, this.dimension / 2);
            this.trChild = new QuadTreeNodeImpl(image, y, x + (this.dimension / 2), 
                    this.dimension / 2);
            this.blChild = new QuadTreeNodeImpl(image, y + (this.dimension / 2), 
                    x, this.dimension / 2);
            this.brChild = new QuadTreeNodeImpl(image, y + (this.dimension / 2), 
                    x + (this.dimension / 2),
                    this.dimension / 2);

            this.isLeaf = false;

            // merge step///////////////////

            if (this.tlChild.color != null && this.tlChild.color == this.trChild.color
                    && this.tlChild.color == this.blChild.color && this.tlChild.color 
                    == this.brChild.color) {

                this.color = this.tlChild.color;
                this.tlChild = null;
                this.trChild = null;
                this.blChild = null;
                this.brChild = null;

                this.isLeaf = true;
            } // end merge

        } else { // basecase 1 by 1
            this.color = image[y][x];
            this.isLeaf = true;
            // no children
            this.tlChild = null;
            this.trChild = null;
            this.blChild = null;
            this.brChild = null;

        }

    }

    /**
     * helper method
     * 
     * @param n
     * @return
     */
    public static boolean isPowerOfTwo(int n) {
        return (n >= 1) && (n == Math.pow(2, Math.round(Math.log(n) / Math.log(2))));
    }

    @Override
    public int getColor(int x, int y) {
        // check that x and y are in bounds
        if (y < this.dimension && x < this.dimension && y >= 0 && x >= 0) {
            return getColorHelper(this, y, x);
        } else {
            // if not in bounds throw exception
            throw new IllegalArgumentException();
        }
    }

    private static int getColorHelper(QuadTreeNodeImpl q, int y, int x) {
        // base case
        if (q.isLeaf) {
            return q.color;
        }
        // else...
        // find the quadrant it's in
        // tl
        if (y < q.dimension / 2 && x < q.dimension / 2) {
            // recurse in tl
            return getColorHelper(q.tlChild, y, x);
        }
        // tr
        if (y < q.dimension / 2 && x >= q.dimension / 2) {
            // recurse in tr
            return getColorHelper(q.trChild, y, x - q.dimension / 2);
        }
        // bl
        if (y >= q.dimension / 2 && x < q.dimension / 2) {
            // recurse in bl
            return getColorHelper(q.blChild, y - q.dimension / 2, x);
        }
        // br
        if (y >= q.dimension / 2 && x >= q.dimension / 2) {
            // recurse in br
            return getColorHelper(q.brChild, y - q.dimension / 2, x - q.dimension / 2);
        }

        return -9999999;
    }

    @Override
    public void setColor(int x, int y, int c) {
        // check in bounds
        if (y < dimension && x < dimension && y >= 0 && x >= 0) {

            setColorHelper(this, y, x, c);
        } else {
            throw new IllegalArgumentException();
        }

    }

    // recursive helper
    private void setColorHelper(QuadTreeNodeImpl q, int y, int x, int c) {
        // base case
        if (q.dimension == 1) {
            q.color = c;
            return;
        }
        // if not a leaf, find quadrant and move on
        if (!q.isLeaf) {
            // recurse as normal in desired quadrant
         // tl
            if (y < q.dimension / 2 && x < q.dimension / 2) {
                // recurse in tl
                setColorHelper(q.tlChild, y, x, c);

            }
            // tr
            if (y < q.dimension / 2 && x >= q.dimension / 2) {
                // recurse in tr
                setColorHelper(q.trChild, y, x - q.dimension / 2, c);
            }
            // bl
            if (y >= q.dimension / 2 && x < q.dimension / 2) {
                // recurse in bl
                setColorHelper(q.blChild, y - q.dimension / 2, x, c);
            }
            // br
            if (y >= q.dimension / 2 && x >= q.dimension / 2) {
                // recurse in br
                setColorHelper(q.brChild, y - q.dimension / 2, x - q.dimension / 2, c);
            }
        } else {
            // make children
            q.tlChild = new QuadTreeNodeImpl(q.color, q.dimension / 2, true);
            q.trChild = new QuadTreeNodeImpl(q.color, q.dimension / 2, true);
            q.blChild = new QuadTreeNodeImpl(q.color, q.dimension / 2, true);
            q.brChild = new QuadTreeNodeImpl(q.color, q.dimension / 2, true);
            // now make 1 not a leaf anymore
            q.isLeaf = false;
    
            //////////////////////////////
            // recurse on desired child as normal now that we have children
            // tl
            if (y < q.dimension / 2 && x < q.dimension / 2) {
                // recurse in tl
                setColorHelper(q.tlChild, y, x, c);
            }
            // tr
            if (y < q.dimension / 2 && x >= q.dimension / 2) {
                // recurse in tr
                setColorHelper(q.trChild, y, x - q.dimension / 2, c);
            }
            // bl
            if (y >= q.dimension / 2 && x < q.dimension / 2) {
                // recurse in bl
                setColorHelper(q.blChild, y - q.dimension / 2, x, c);
            }
            // br
            if (y >= q.dimension / 2 && x >= q.dimension / 2) {
                // recurse in br
                setColorHelper(q.brChild, y - q.dimension / 2, x - q.dimension / 2, c);
            }

        }

        ///////////////// do i merge here?
        ////// merge step

        if (q.tlChild.isLeaf && q.trChild.isLeaf && q.blChild.isLeaf && q.brChild.isLeaf) {
            if (q.tlChild.color != null && q.tlChild.color == q.trChild.color && 
                    q.tlChild.color == q.blChild.color
                    && q.tlChild.color == q.brChild.color) {
                q.color = q.tlChild.color;
                q.tlChild = null;
                q.trChild = null;
                q.blChild = null;
                q.brChild = null;

                q.isLeaf = true;
            }
        } // end merge

    } // setColorHelper

    @Override
    public QuadTreeNode getQuadrant(QuadName quadrant) {
        if (quadrant == QuadName.TOP_LEFT) {
            return tlChild;
        }
        if (quadrant == QuadName.TOP_RIGHT) {
            return trChild;
        }
        if (quadrant == QuadName.BOTTOM_LEFT) {
            return blChild;
        }
        if (quadrant == QuadName.BOTTOM_RIGHT) {
            return brChild;
        }
        return null;
    }

    @Override
    public int getDimension() {
        return this.dimension;
    }

    @Override
    public int getSize() {
        return getSizeHelper(this);
    }

    private int getSizeHelper(QuadTreeNodeImpl q) {

        if (q.isLeaf) {
            return 1;
        }

        return 1 + getSizeHelper(q.tlChild) + 
                    getSizeHelper(q.trChild) + getSizeHelper(q.blChild)
                    + getSizeHelper(q.brChild);

    }

    @Override
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * idea: will be called on the root of a tree
     */

    // gonna def be a recursive method, actually let helper method be recursice
    @Override
    public int[][] decompress() {

        int[][] d = new int[dimension][dimension];
        // rec helper method call
        decompressHelper(this, d, 0, 0);

        return d;
    }

    // recursive solver for decompress
    private void decompressHelper(QuadTreeNodeImpl q, int[][] d, int y, int x) {
        if (q.isLeaf) {
            // color the corresponding region
            for (int i = y; i < y + q.dimension; i++) {
                for (int j = x; j < x + q.dimension; j++) {
                    d[i][j] = q.color;
                }
            }
        } else {
            decompressHelper(q.tlChild, d, y, x);
            decompressHelper(q.trChild, d, y, x + q.dimension / 2);
            decompressHelper(q.blChild, d, y + q.dimension / 2, x);
            decompressHelper(q.brChild, d, y + q.dimension / 2, x + q.dimension / 2);
        }
    }

    @Override
    public double getCompressionRatio() {
        double p = this.getDimension() * this.getDimension() + 0.0; // = area of orig img
        double n = this.getSize() + 0.0; // = nodes in tree
        return (n / p);
    }

}
