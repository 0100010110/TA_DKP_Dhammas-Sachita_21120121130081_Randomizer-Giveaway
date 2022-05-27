package antri;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import javax.swing.*;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;

public class main extends Component {
    public JPanel mainbox;
    private JLabel lbBanyak;
    private JButton TakeButton;
    private JTextArea txMember;
    private JButton NextButton;
    private JTextField txNama;
    private JButton resetButton;
    private JLabel tag;

    Queue antrian =  new LinkedList<>();
    Queue nama    =  new LinkedList<>();



    Random angkaRandom = new Random();
    int nomer = 0;

    public main()
    {
        inisialisasi() ;
        tag.setVisible(false);
        TakeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ambilantre();
                NextButton.setEnabled(true);
            }
        });
        NextButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                int b = (int)( Math.random() * antrian.size());
                for (int i =1; i <= b; i++) {

                    Next();
                    tag.setVisible(true);
                    NextButton.setEnabled(false);
                }

            }
        });
        txNama.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                super.keyPressed(e);
                char c = e.getKeyChar();
                txNama.setEditable(Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c));

            }
        });
        resetButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
    }


    private void inisialisasi()
    {
        lbBanyak.setText("0");
    }

    public void ambilantre()
    {
        if (txNama.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Silakan Masukkan Nama Terlebih Dahulu !");
        }
        else
        {
            nomer++;
            String nm = txNama.getText();
            String ant = "Antrian " + nomer;
            antrian.add(ant);
            nama.add(nm);
            String jml = String.valueOf(antrian.size());
            lbBanyak.setText(jml);
            txMember.append(nm + "\n");

            txNama.setText("");
        }

    }
    private void Next(){
        if(antrian.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Mohon masukkan nama terlebih dahulu !");
        }
        antrian.poll();
        txMember.setText("");
        nama.poll();
        String jml = String.valueOf(antrian.size());
        nama.forEach((Object element) -> {
            txMember.append("" + element + "\n" );
        });
    }

    public void reset()
    {
        JOptionPane.showMessageDialog(null, "done");
        lbBanyak.setText("0");
        txMember.setText("");
        antrian.clear();
        nama.clear();
        nomer = 0;
    }

    public static void main(String[] args) {
        JFrame utama = new JFrame("main");
        utama.setContentPane(new main().mainbox);
        utama.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        utama.setLocationRelativeTo(null);
        utama.pack();
        utama.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}