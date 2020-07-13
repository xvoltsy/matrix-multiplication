package com.test.multipliers.matrix;

import com.test.multipliers.Multiplier;
import com.test.object.Matrix;
import com.test.util.MatrixUtil;

public class MatrixSequentialMultiplier implements Multiplier<Matrix>
{
    public Matrix multiply(Matrix first, Matrix second)
    {
        int[][] result = new int[first.getArray().length][first.getArray()[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = MatrixUtil.multiplyMatricesCell(first.getArray(), second.getArray(), row, col);
            }
        }
        return new Matrix(result);
    }
}
