package com.wer.bloomfilter.filter;

import lombok.Data;

import java.util.Random;

/**
 *  h3哈希函数
 */
@Data
public class H3HashFunction{

    private class H3Matrix{
        public int col[] = new int[32];
    }
    private int h3_hash_num;
    private H3Matrix h3_matrix[];
    public H3HashFunction(int h3_hash_num){
        this.h3_hash_num = h3_hash_num;
        this.initH3Matrix(h3_hash_num);
    }

    public void initH3Matrix(int h3_hash_num){
        this.h3_matrix = new H3Matrix[h3_hash_num];
        Random random = new Random();
        for(int i = 0;i<h3_hash_num;i++){
            this.h3_matrix[i] = new H3Matrix();
            for(int j = 0;j<32;j++){
                int r = 0;
                for(int q = 0;q<4;q++){
                    int randf = random.nextInt()*256;
                    r += randf;
                    if(q == 3){
                        break;
                    }
                    r <<= 8;
                }
                this.h3_matrix[i].col[j] = r;
            }
        }
    }

    public int h3Hash(int h3_row,int h3_hash_code,int matrix_index){
        int matrix_col = 0xffffffff;
        int colIndex = 0x00000001;
        for(int i = 0;i<32;i++){
            if((h3_hash_code & colIndex) == colIndex){
                matrix_col ^= h3_matrix[matrix_index].col[i] >> (32-h3_row);
            }
            colIndex <<= 1;
        }
        int tmp = matrix_col;
        if(tmp <0){
            tmp = -tmp;
        }
        matrix_col = tmp % (int)Math.pow(2,h3_row);
        return matrix_col;
    }


}
