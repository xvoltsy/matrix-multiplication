package com.test.multipliers.matrix;

import com.test.multipliers.Multiplier;
import com.test.object.Matrix;
import com.test.util.MatrixUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MatrixParallelMultiplier implements Multiplier<Matrix>
{
    @Override
    public Matrix multiply(Matrix first, Matrix second)
    {
        int[][] result = new int[first.getArray().length][first.getArray()[0].length];
        int cores = Runtime.getRuntime().availableProcessors();
        ForkJoinPool forkJoinPool = new ForkJoinPool(cores);
        forkJoinPool.invoke(new MultipleAction(result, first.getArray(), second.getArray(), 0, result.length));
        return new Matrix(result);
    }

    private static class MultipleAction extends RecursiveAction
    {
        private static final int THRESHOLD = 1;

        private int[][] result;
        private int[][] firstMatrix;
        private int[][] secondMatrix;
        private int start;
        private int end;

        public MultipleAction(int[][] result, int[][] firstMatrix, int[][] secondMatrix, int start, int end)
        {
            this.result = result;
            this.firstMatrix = firstMatrix;
            this.secondMatrix = secondMatrix;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute()
        {
            int length = end - start;
            if ( length > THRESHOLD )
            {
                ForkJoinTask.invokeAll(createSubActions());
            }
            else
            {
                for (int row = start; row < end; row++)
                {
                    for (int col = 0; col < result[row].length; col++)
                    {
                        result[row][col] = MatrixUtil.multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
                    }
                }
            }

        }

        private List<MultipleAction> createSubActions()
        {
            int length = end - start;
            List<MultipleAction> subActions = new ArrayList<>();

            subActions.add(new MultipleAction(result, firstMatrix, secondMatrix, start, start + length / 2));
            subActions.add(new MultipleAction(result, firstMatrix, secondMatrix, start + length / 2, end));
            return subActions;
        }
    }
}
