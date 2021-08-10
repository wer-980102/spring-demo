package com.wer.bloomfilter.filter;

/**
 *  普通哈希函数
 */
public class HashFunctions {
    public static short xor16(byte buf[]){
        short hash = 0;
        short i = 0;

        while (i+ 2 <= buf.length)
        {
            hash ^= buf[i+1] << 8;
            hash ^= buf[i];
            i += 2;
        }

        while (i<buf.length)
        {
            hash ^= buf[i]<<(8*(i%2));
            i++;
        }
        return hash;
    }

    public static int mix(int a,int b,int c){
        a -= b; a -= c; a ^= (c>>13);
        b -= c; b -= a; b ^= (a<<8);
        c -= a; c -= b; c ^= (b>>13);
        a -= b; a -= c; a ^= (c>>12);
        b -= c; b -= a; b ^= (a<<16);
        c -= a; c -= b; c ^= (b>>5);
        a -= b; a -= c; a ^= (c>>3);
        b -= c; b -= a; b ^= (a<<10);
        c -= a; c -= b; c ^= (b>>15);
        return c;
    }

    public static int bob(byte buf[]){
        int a,b,c,length;
        length = buf.length;
        a = b = 0x9e3779b9;  /* the golden ratio; an arbitrary value */
        c = 0;         /* variable initialization of internal state */
        /*---------------------------------------- handle most of the key */
        int i = 0;
        while (length >= 12)
        {
            a += (buf[i+0]+((short)buf[i+1]<<8)+((short)buf[i+2]<<16) +((short)buf[i+3]<<24));
            b += (buf[i+4]+((short)buf[i+5]<<8)+((short)buf[i+6]<<16) +((short)buf[i+7]<<24));
            c += (buf[i+8]+((short)buf[i+9]<<8)+((short)buf[i+10]<<16)+((short)buf[i+11]<<24));
            mix(a,b,c);
            i += 12; length -= 12;
        }

        /*------------------------------------- handle the last 11 bytes */
        c += buf.length;
        switch(length)              /* all the case statements fall through */
        {
            case 11: c+=((int)buf[i+10]<<24);
            case 10: c+=((int)buf[i+9]<<16);
            case 9 : c+=((int)buf[i+8]<<8);
                /* the first byte of c is reserved for the length */
            case 8 : b+=((int)buf[i+7]<<24);
            case 7 : b+=((int)buf[i+6]<<16);
            case 6 : b+=((int)buf[i+5]<<8);
            case 5 : b+=buf[i+4];
            case 4 : a+=((int)buf[i+3]<<24);
            case 3 : a+=((int)buf[i+2]<<16);
            case 2 : a+=((int)buf[i+1]<<8);
            case 1 : a+=buf[i+0];
                /* case 0: nothing left to add */
        }
        mix(a,b,c);
        /*-------------------------------------------- report the result */
        return c;
    }

    /*Jenkins's one-at-a-time hash*/
    public static int oaat(byte buf[])
    {
        int len = buf.length;
        int hash= 0 ;
        for (int i=0; i<len; ++i)
        {
            hash += buf[i];
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        return hash;
    }

    public static int additiveHash(String key) {
        int n = key.length();
        int hash = n;
        for (int i = 0; i < n; i++)
            hash += key.charAt(i);
        return hash ^ (hash >> 10) ^ (hash >> 20);
    }

    public static int rotatingHash(String key) {
        int n = key.length();
        int hash = n;
        for (int i = 0; i < n; i++)
            hash = (hash << 4) ^ (hash >> 28) ^ key.charAt(i);
        return (hash & 0x7FFFFFFF);
    }
}
