/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sockettcpcliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author FMDUQUE
 */
//Cliente
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            String sair = "S";
            
            while(sair.equals("S")){
                
                            //1 - Abrir a conexão
            Socket clientSocket = new Socket("192.168.137.1", 6789);
            
                 //2 - Pegando os streams de entrada e saída
                DataInputStream inbound = new DataInputStream( clientSocket.getInputStream() );
                DataOutputStream outbound = new DataOutputStream( clientSocket.getOutputStream() );

                //3 - Utilizando os streams de entrada e saída
                String nome = new String (JOptionPane.showInputDialog(null, "Informe seu nome para ter acesso a sua conta"));
                Integer opcao = new Integer (JOptionPane.showInputDialog(null, "Informe a operação desejada. 1- Saldo 2- Deposito 3- Saque"));

                //VARIAVEIS AUXILIAR    
                Integer valor = 0;

                //JOptionPane.showMessageDialog(null, opcao);

                if(opcao == 2 || opcao == 3){
                    valor = new Integer (JOptionPane.showInputDialog(null, "Informe o valor para a operação"));
                    while(valor < 0){
                        valor = new Integer (JOptionPane.showInputDialog(null, "Informe um valor válido."));
                    }
                }

                System.out.println(new Date() + " - Aguardando ...");
                outbound.writeInt(opcao);
                outbound.writeUTF(nome);
                outbound.writeInt(valor);


                //RECEBENDO AS VARIAVEIS DO SERVIDOR
                int numero = inbound.readInt();
                String resposta = inbound.readUTF();
                int dinheiro = inbound.readInt();

                //MOSTRANDO RESULTADOS
                System.out.println(new Date() + " - Resposta do servidor");
                JOptionPane.showMessageDialog(null, resposta + dinheiro);
                //System.out.println(s);

                //4 - Fechando os streams de entrada e saída
                inbound.close() ;
                outbound.close() ;
                
                            //5 - Fechando o socket
            clientSocket.close();
                
                sair = new String (JOptionPane.showInputDialog(null, "Deseja efetuar uma nova operação ? s/n"));
                sair = sair.toUpperCase();
            }



        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
