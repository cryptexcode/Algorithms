/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Longest Common Subsequence(LCS) JAVA Implementation
 * @author Sudipta Kar
 */
public class LCS {
    private String stringOne, stringTwo, formulatedString;
    
    public LCS(String s1, String s2){
        this.stringOne = s1;
        this.stringTwo = s2;
    }
    
    public String getLCS(){
        
        int l1 = this.stringOne.length();
        int l2 = this.stringTwo.length();
        int[][] map = new int[l1+1][l2+1];
        int[][] dirMap = new int[l1+1][l2+1];
        
        for(int i=0; i<=l1; i++) map[i][0] = 0;
        for(int i=0; i<=l2; i++) map[0][i] = 0;
        
        for(int i=1; i<=l1; i++)
        {
            for(int j=1; j<=l2; j++)
            {
                if(this.stringOne.charAt(i-1) == this.stringTwo.charAt(j-1))
                {
                    dirMap[i][j] = 1;
                    map[i][j] = Math.max(map[i-1][j],  map[i][j-1]) + 1;
                }
                else
                {
                    if(map[i-1][j] > map[i][j-1]){
                        dirMap[i][j] = -1;
                        map[i][j] = map[i-1][j];
                    }else{
                        dirMap[i][j] = 0;
                        map[i][j] = map[i][j-1];
                    }
                }
            }
        }              
        
        this.formulatedString = "";        
        backTrack(map, dirMap, l1, l2);                
        
        return this.formulatedString;
    }
    
    
    private String backTrack(int[][] map, int[][] dirMap, int l1, int l2){        
        
        if(l1==0 || l2==0) return "";
        else if(dirMap[l1][l2] == 1){
            this.formulatedString = this.stringOne.charAt(l1-1) + this.formulatedString;
            return backTrack(map, dirMap, l1-1, l2-1);
        }
        else{
            if(dirMap[l1][l2] == -1){
                return backTrack(map, dirMap, l1-1, l2);
            }else{
                return backTrack(map, dirMap, l1, l2-1);
            }
        }
        
    }
    
    public static void main(String[] args) {
        LCS lcs = new LCS("XMJYAUZ", "MZJAWXU");
        System.out.println( lcs.getLCS() );
        
    }
    
}
