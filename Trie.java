
package trie;


    
    
    public class Trie <T> {

        sent raiz;
        char [] simbolos;

        public Trie(char [] sim){
            raiz = new sent();
            sort(sim);
            simbolos = sim.clone();
        }

        public boolean isEmpty(){
            if (raiz.getSig() == null)
                return true;
            else
                return false;
        }

        public NodoTrie getRaiz(){
            return raiz.getSig();
        }


        private static void ordenar(char [] arr, int minimo, int maximo, int[] sum){
            char [] temp;
            int indx, left, right;
            if (minimo >= maximo - 1)
                return;
            int tam = maximo - minimo + 1, mitad = (maximo + minimo)/2;
            temp = new char[tam];
            ordenar(arr, minimo, mitad, sum);
            ordenar(arr, mitad + 1, maximo, sum);
            right = mitad + 1;
            left = minimo; 
            
            for(int i = 0; i < tam; i++){
                sum[0]++;
                if(left <= mitad && right <= maximo){
                    if(arr[left] < arr[right])
                        temp[i] = arr[left++];
                    else
                        temp[i] = arr[right++];
                }//if
                else{
                    if(left <= mitad)
                        temp[i] = arr[left++];
                    else   
                        temp[i] = arr[right++];
                }//else
            }//for


            for(int i = 0; i < temp.length; i++){
                sum[0]++;
                arr[minimo + i] = temp[i];
            }//for

        }//method

        
        public void sort(char[] ar){
            int [] arr = {0};
            ordenar(ar, 0, ar.length - 1, arr);

        }
                
        public int busca(String llave){
            if (isEmpty())
                return 0;
            return busca(llave, raiz.getSig());
        }//method

        private int busca(String llave, NodoTrie actual){
            if (actual == null){
                return 0;
            }
            if (llave == null || llave.equals("")){            
                return actual.cont;
            }
            else{
                return busca(llave.substring(1), actual.getNodo(llave.charAt(0)));
            }//else
        }//method

        public void add(String key){
            if (isEmpty()){
                NodoTrie n = new NodoTrie(simbolos);
                raiz.setSig(n);
            }            
            raiz.getSig().add(key);
        }

        public boolean delete(String llave){
            if (isEmpty())
                return false;
            return delete(llave, raiz.getSig(), llave);
        }

        private boolean delete(String llave, NodoTrie actual, String llaveCompleta){

            if (llave.length() == 1){

                if (actual.getNodo(llave.charAt(0)) == null || actual.getNodo(llave.charAt(0)).cont <= 0)
                    return false;

                if (actual.getNodo(llave.charAt(0)).cont > 1)
                actual.getNodo(llave.charAt(0)).cont --;                
                else{

                    if (actual.getNodo(llave.charAt(0)).isEmpty()){                    
                        limpia(actual.getNodo(llave.charAt(0)), llaveCompleta);
                    }
                    else{
                        actual.getNodo(llave.charAt(0)).cont --;
                    }

                }
                return true;
            }
            else{
                if (actual.getNodo(llave.charAt(0)) == null)
                    return false;
                return delete(llave.substring(1), actual.getNodo(llave.charAt(0)), llaveCompleta);
            }
        }

        public void limpia(NodoTrie n, String llave){
            while(n.getPapa() != null && llave.length() > 1 && n.emptyException(llave.charAt(llave.length()-1))){
                n = n.getPapa();
                n.hijos[n.pos(llave.charAt(llave.length()-1))] = null;
                llave = llave.substring(0,llave.length()-1);
            }//while
            if (n.getPapa() == null && n.emptyException(llave.charAt(0)))
                raiz.setSig(null);
        }
        
        public String print(){
            StringBuilder res = new StringBuilder();
            Trie.this.print(raiz.getSig(), res, "");
            return res.toString();
        }

        private void print(NodoTrie<T> n, StringBuilder res, String s){
            int i;
            for (i = 0; i < n.cont; i++){
                res.append(s).append("\n");
            }
            for (i = 0; i < simbolos.length; i++){
                if (n.hijos[i] != null)
                    Trie.this.print(n.hijos[i], res, s + simbolos[i]);
            }
        }
        
        
        public static void main (String args[]){
            char keys [] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
            Trie<Integer> t = new Trie(keys);
            System.out.println(t.print());
        } 
        
        
        private class sent{
            NodoTrie raiz;
            public sent(){
                raiz = null;
            }

            public void setSig(NodoTrie n){
                raiz = n;
            }

            public NodoTrie getSig(){
                    return raiz;
            }
        }





    }
