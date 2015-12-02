/* 
The MIT License (MIT)
Copyright (c) 2015 synchrocom-proto.com

Данная лицензия разрешает лицам, получившим копию данного программного обеспечения
и сопутствующей документации (в дальнейшем именуемыми «Программное Обеспечение»), 
безвозмездно использовать Программное Обеспечение без ограничений, включая 
неограниченное право на использование, копирование, изменение, добавление, 
публикацию, распространение, сублицензирование и/или продажу копий Программного 
Обеспечения, а также лицам, которым предоставляется данное Программное Обеспечение, 
при соблюдении следующих условий: 

Кроме содержимого в этом уведомлении, имя (имена) вышеуказанных держателей авторских
прав не должно быть использовано в рекламе или иным способом, чтобы увеличивать 
продажу, использование или другие работы в этом Программном обеспечении без 
предшествующего письменного разрешения.

Указанное выше уведомление об авторском праве и данные условия должны быть включены
во все копии или значимые части данного Программного Обеспечения.


ДАННОЕ ПРОГРАММНОЕ ОБЕСПЕЧЕНИЕ ПРЕДОСТАВЛЯЕТСЯ «КАК ЕСТЬ», БЕЗ КАКИХ-ЛИБО ГАРАНТИЙ, 
ЯВНО ВЫРАЖЕННЫХ ИЛИ ПОДРАЗУМЕВАЕМЫХ, ВКЛЮЧАЯ ГАРАНТИИ ТОВАРНОЙ ПРИГОДНОСТИ, 
СООТВЕТСТВИЯ ПО ЕГО КОНКРЕТНОМУ НАЗНАЧЕНИЮ И ОТСУТСТВИЯ НАРУШЕНИЙ, НО НЕ 
ОГРАНИЧИВАЯСЬ ИМИ. НИ В КАКОМ СЛУЧАЕ АВТОРЫ ИЛИ ПРАВООБЛАДАТЕЛИ НЕ НЕСУТ 
ОТВЕТСТВЕННОСТИ ПО КАКИМ-ЛИБО ИСКАМ, ЗА УЩЕРБ ИЛИ ПО ИНЫМ ТРЕБОВАНИЯМ, В ТОМ 
ЧИСЛЕ, ПРИ ДЕЙСТВИИ КОНТРАКТА, ДЕЛИКТЕ ИЛИ ИНОЙ СИТУАЦИИ, ВОЗНИКШИМ ИЗ-ЗА 
ИСПОЛЬЗОВАНИЯ ПРОГРАММНОГО ОБЕСПЕЧЕНИЯ ИЛИ ИНЫХ ДЕЙСТВИЙ С ПРОГРАММНЫМ ОБЕСПЕЧЕНИЕМ.
*/
package synchrocom.sctp.sctpa;

import java.io.Serializable;

/**
 * 
 * @author Ilya Juhnowski
 */
public class SCTPA  implements Serializable{
    public Header hdr = new Header();
    public Object body;
        
    public class Header  implements Serializable{
        public boolean URG;
        public boolean ACK;
        public boolean PSH;
        public boolean RST;
        public boolean SYN;
        public boolean FIN;
        public boolean Res_1;
        public boolean Res_2;
        public short Window;
        public short PortSender;
        public short PortReceiver;
        public int NumQueue;
        public int NumAcc;
        public short CheckSum;
        public short UrgPtr;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[[[ \n");
        sb.append("SCTPA-Header:").append("\n");
        sb.append("\tURG:").append(hdr.URG).append("  ACK:").append(hdr.ACK)
                .append("  PSH:").append(hdr.PSH).append("  RST:")
                .append(hdr.RST).append("  SYN:").append(hdr.SYN)
                .append("  FIN:").append(hdr.FIN).append("\n");
        sb.append("\tPortSnd:").append(hdr.PortSender).append("  PortRcv:")
                .append(hdr.PortReceiver).append("\n");
        sb.append("\tNumQueue:").append(hdr.NumQueue).append("\n");
        sb.append("\tNumAcc:").append(hdr.NumAcc).append("\n");
        sb.append("\tCheckSum:").append(hdr.CheckSum).append("\n");
        sb.append("\nSCTPA-Body: \n");
        sb.append(body.toString()).append("\n");
        sb.append("]]] \n");
        return sb.toString();        
    }    
}
