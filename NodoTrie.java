package trie;


    public class NodoTrie <T>{

        NodoTrie<T> hijos [];
        NodoTrie<T> paps;
        static char [] keys;
        int cont;
        T elem;

        public NodoTrie(char [] ord){
            hijos = new NodoTrie[ord.length];
            keys = ord;
            cont = 0;
            paps = null;
        }

        public NodoTrie(){
            hijos = new NodoTrie[keys.length];
            cont = 0;
            paps = null;
        }//builder    

        public NodoTrie(String key){
            hijos = new NodoTrie[keys.length];
            if (key != null)           
                add(key);
            paps = null;
        }//builder

        public void setNodo(char c, NodoTrie n){
            hijos[pos(c)] = n;
        }

        public NodoTrie getNodo(char c){
            return hijos[pos(c)];
        }

        public NodoTrie<T> getPapa(){
            return paps;
        }//method

        public NodoTrie(String key, NodoTrie pa){
            hijos = new NodoTrie[keys.length];
            if (key != null)           
                add(key);
            cont++;
            paps = pa;
        }//builder

        public void add(String key){
            if (key == null){
                System.out.println("Null pointer");
            }            
            if (key.length() == 1){
                if (hijos[pos(key.charAt(0))] == null){
                    hijos[pos(key.charAt(0))] = new NodoTrie();
                    hijos[pos(key.charAt(0))].cont++;
                    hijos[pos(key.charAt(0))].paps = this;
                }
                else
                    hijos[pos(key.charAt(0))].cont++;
            }
            else{
                if (hijos[pos(key.charAt(0))] == null){
                    hijos[pos(key.charAt(0))] = new NodoTrie(key.substring(1));            
                    hijos[pos(key.charAt(0))].paps = this;
                }
                else{
                    hijos[pos(key.charAt(0))].add(key.substring(1));
                }
            }
        }

        public int pos(char c){
            int i = 0;
            while (i < keys.length && keys[i] != c)
                i++;
            if (i >= keys.length)
                return -1;
            else
                return i;
        }  

        public boolean emptyException(char c){
            boolean res = true;
            int i = 0, p = pos(c);
            while (res && i < hijos.length){
                if (i != p)
                    res = hijos[i] == null;
                i++;
            }
            return res;
        }

        public boolean isEmpty(){
            boolean res = false;
            int j = 0;
            while (j < hijos.length && !res){            
                res = hijos[j] ==null ;
                j++;
            }
            return res;
        }

    }

