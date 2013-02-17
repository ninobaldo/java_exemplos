/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.util;

import java.awt.*;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.openswing.swing.client.DateControl;

/**
 *
 * @author mjustino
 */
public class FuncoesInterface {

//    public static Object loadObjectTpgManut(JTable grdObj) {
//        ObjectTableModel<Class<?>> tm = (ObjectTableModel<Class<?>>) grdObj.getModel();
//        return tm.getValue(grdObj.getSelectedRow());
//    }
    /**
     * Limpa os campos de formulário
     *
     * @param container
     */
    public static void limparCampos(Container container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof DateControl) {
                DateControl field = (DateControl) component;
                field.setDate(new Date());
            } else if (component instanceof JFormattedTextField) {
                JFormattedTextField field = (JFormattedTextField) component;
                field.setValue(null);
            } else if (component instanceof JTextField) {
                JTextField field = (JTextField) component;
                field.setText("");
            } else if (component instanceof JTextArea) {
                JTextArea area = (JTextArea) component;
                area.setText("");
            } else if (component instanceof Container) {
                limparCampos((Container) component);
            }
        }
    }

    /**
     *
     * @param component
     * @param cursor
     */
    public static void setCursor(Component component, TipoCursor cursor) {
        if (component != null) {
            component.setCursor(Cursor.getPredefinedCursor(cursor.getValor()));
        }
    }

    /**
     * Define o layout base para cadastros com alinhamento em horizontal.
     *
     * @param layoutConstraints
     * @return
     */
    public static GridBagLayout getGridBagLayoutCadastros(GridBagConstraints layoutConstraints) {
        if (layoutConstraints == null) {
            layoutConstraints = new GridBagConstraints();
        }

        GridBagLayout gbl = new GridBagLayout();
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.weightx = 1;
        return gbl;
    }

    /**
     * Enum que determina o tipo de cursor a ser utilizado.
     */
    public enum TipoCursor {

        DEFAULT_CURSOR {

            @Override
            public int getValor() {
                return 0;
            }
        },
        CROSSHAIR_CURSOR {

            @Override
            public int getValor() {
                return 1;
            }
        },
        TEXT_CURSOR {

            @Override
            public int getValor() {
                return 2;
            }
        },
        WAIT_CURSOR {

            @Override
            public int getValor() {
                return 3;
            }
        },
        SW_RESIZE_CURSOR {

            @Override
            public int getValor() {
                return 4;
            }
        },
        SE_RESIZE_CURSOR {

            @Override
            public int getValor() {
                return 5;
            }
        },
        NW_RESIZE_CURSOR {

            @Override
            public int getValor() {
                return 6;
            }
        },
        NE_RESIZE_CURSOR {

            @Override
            public int getValor() {
                return 7;
            }
        },
        N_RESIZE_CURSOR {

            @Override
            public int getValor() {
                return 8;
            }
        },
        S_RESIZE_CURSOR {

            @Override
            public int getValor() {
                return 9;
            }
        },
        W_RESIZE_CURSOR {

            @Override
            public int getValor() {
                return 10;
            }
        },
        E_RESIZE_CURSOR {

            @Override
            public int getValor() {
                return 11;
            }
        },
        HAND_CURSOR {

            @Override
            public int getValor() {
                return 12;
            }
        },
        MOVE_CURSOR {

            @Override
            public int getValor() {
                return 13;
            }
        };

        public int getValor() {
            return this.getValor();
        }
    }
}
