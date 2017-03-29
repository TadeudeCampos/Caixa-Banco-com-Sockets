/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sockettcpserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FMDUQUE
 */
//Server
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            //Tipicamente o comportamento do servidor é ficar em loop, aguardando novas conexões
            while (true) {
                 //1 - Criar o server socket:
                System.out.println(new Date() + " Aguardando Conexão");
                ServerSocket serverSocket = new ServerSocket(6789);

                //2 - Aguardar conexoes de clientes:
                Socket clientSocket = serverSocket.accept();

                //3 - Criar streams de entrada e saída do cliente:
                DataInputStream inbound = new DataInputStream( clientSocket.getInputStream() ) ;
                DataOutputStream outbound = new DataOutputStream( clientSocket.getOutputStream() ) ;

                //4 - Conversando com o cliente:
                int k = inbound.readInt();
                String s = inbound.readUTF() ;
                int valor = inbound.readInt() ;
                
                int var1 = 1500;//renan
                int var2 = 1450;//vitor 
                int var3 = 1234;//claudio
                int var4 = 0;   // novo
                
                if(k==1)//Saldo 
                {
                    if(s.equals("Renan"))
                    {
                        valor = var1;
                        outbound.writeInt(k);
                        outbound.writeUTF(" Bom dia "+ s.toUpperCase()+"\n Seu saldo é de : R$");
                        outbound.writeInt(valor);
                    }
                    else
                        if(s.equals("Vitor"))
                            {
                                valor = var2;
                                outbound.writeInt(k);
                                outbound.writeUTF(" Bom dia "+ s.toUpperCase()+"\n Seu saldo é de : R$");
                                outbound.writeInt(valor);
                            }
                            else
                                if(s.equals("Claudio"))
                                    {
                                        valor = var3;
                                        outbound.writeInt(k);
                                        outbound.writeUTF(" Bom dia "+ s.toUpperCase()+"\n Seu saldo é de : R$");
                                        outbound.writeInt(valor);
                                    }
                                    else
                                        if(s.equals("Novo"))
                                            {
                                                valor = var4;
                                                outbound.writeInt(k);
                                                outbound.writeUTF(" Bom dia "+ s.toUpperCase()+"\n Seu saldo é de : R$");
                                                outbound.writeInt(valor);
                                            }
                                             else
                                                {
                                                    valor = var4;
                                                    outbound.writeInt(k);
                                                    outbound.writeUTF(" Usuário: "+ s.toUpperCase()+" Desconhecido \n codigo do erro:");
                                                    outbound.writeInt(valor+404);
                                                }

                }
                else 
                    if(k==2)//Deposito
                    {
                        if(s.equals("Renan"))
                        {
                            var1 = var1 + valor;
                            valor = var1;
                            outbound.writeInt(k);
                            outbound.writeUTF(" Deposito na conta de:  "+ s.toUpperCase() + "/n Concluido com sucesso! /n no valor de :");
                            outbound.writeInt(valor);
                        }
                        else
                            if(s.equals("Vitor"))
                            {
                                var2 = var2 + valor;
                                valor = var2;
                                outbound.writeInt(k);
                                outbound.writeUTF(" Deposito na conta de:  "+ s.toUpperCase() + "/n Concluido com sucesso! /n no valor de :");
                                outbound.writeInt(valor);
                            }
                            else
                                if(s.equals("Claudio"))
                                {
                                    var3 = var3 + valor;
                                    valor = var3;
                                    outbound.writeInt(k);
                                    outbound.writeUTF(" Deposito na conta de:  "+ s.toUpperCase() + "/n Concluido com sucesso! /n no valor de :");
                                    outbound.writeInt(valor);
                                }
                                else
                                    if(s.equals("Novo"))
                                    {
                                        var4 = var4 + valor;
                                        valor = var4;
                                        outbound.writeInt(k);
                                        outbound.writeUTF(" Deposito na conta de:  "+ s.toUpperCase() + "/n Concluido com sucesso! /n no valor de :");
                                        outbound.writeInt(valor);
                                    }
                                    else
                                        {
                                            valor = var4;
                                            outbound.writeInt(k);
                                            outbound.writeUTF(" Usuário: "+ s.toUpperCase()+" Desconhecido \n codigo do erro:");
                                            outbound.writeInt(valor+404);
                                        }
                                                 
                        
                    }
                    else 
                        if(k==3)//Saque
                        {
                            outbound.writeInt(k);
                            outbound.writeUTF(++k +" Saque"+ s.toUpperCase() + "! blz?");
                        }
                        else 
                            { 
                                outbound.writeInt(k);
                                outbound.writeUTF("Opção Invalida");
                            }

                //5 - Fechando streams e socket cliente:
                inbound.close () ;
                outbound.close () ;
                clientSocket.close() ;

                //6 - Fechando o socket servidor:
                System.out.println(new Date() + " Fechando conexão");
                serverSocket.close() ;
            }
           

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


}
