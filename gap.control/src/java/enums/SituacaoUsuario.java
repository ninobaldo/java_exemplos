package enums;

public enum SituacaoUsuario {

    NOVO {

        @Override
        public String toString() {
            return "Novo";
        }
    },
    
    INATIVO {

        @Override
        public String toString() {
            return "Inativo";
        }
    },
    
    ATIVO {

        @Override
        public String toString() {
            return "Ativo";
        }
    };
    
    public static boolean existe(String situacao){
        for (SituacaoUsuario tipoUsuario : SituacaoUsuario.values()) {
            if(tipoUsuario.toString().equals(situacao))
                return true;
        }
        return false;
    }
}