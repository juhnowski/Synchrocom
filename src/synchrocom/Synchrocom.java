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
package synchrocom;

import synchrocom.sctp.sctps.SCTPS;
import synchrocom.sctp.sctpa.SCTPA;
import synchrocom.sctp.SCTP;
import synchrocom.sca.SCA;
import static synchrocom.sca.SCAUtil.MASTER_SYNC_HOST_ADDRESS;
import static synchrocom.sca.SCAUtil.SLAVE_HOST_ADDRESS_MIN;

import static synchrocom.sctp.SCTPUtil.SCTP_PROTOCOL_SCTPS;
import static synchrocom.sctp.SCTPUtil.SCTP_PROTOCOL_SCTPA;

import static synchrocom.sctp.sctps.SCTPSUtil.SCTPS_SoC;
        
/**
 *
 * @author Ilya
 */
public class Synchrocom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SCA sca_master = new SCA();
        String msg = new String("Hello world");
        
        sca_master.hdr.SourceAddress = MASTER_SYNC_HOST_ADDRESS;
        sca_master.hdr.DestinationAddress = SLAVE_HOST_ADDRESS_MIN;
        sca_master.hdr.TotalLength = 123;
        sca_master.hdr.Identification = 1;
        sca_master.hdr.Flags = 1;
        sca_master.hdr.TTL = 2;
        sca_master.hdr.Protocol = 1;
        sca_master.hdr.HeaderChecksum = 123;
        sca_master.body = msg;
        
        System.out.println("==================== SCA TEST =====================");
        System.out.println(sca_master);
        
        SCTP sctp = new SCTP();
        sctp.hdr.Protocol = SCTP_PROTOCOL_SCTPS;
        
        SCTPS sctps = new SCTPS();
        sctps.hdr.Type = SCTPS_SoC;
        sctps.body = new String("Hello SCTPS SoC!");
        
        sctp.body = sctps;
        sca_master.body = sctp;
        System.out.println("==================== SCTPS TEST =====================");
        System.out.println(sca_master);
        
        SCTPA sctpa = new SCTPA();
        sctpa.hdr.URG = false;
        sctpa.hdr.ACK = false;
        sctpa.hdr.PSH = false;
        sctpa.hdr.RST = false;
        sctpa.hdr.SYN = false;
        sctpa.hdr.FIN = false;
        sctpa.hdr.Window = 12345;
        sctpa.hdr.PortSender = 1001;
        sctpa.hdr.PortReceiver = 1001;
        sctpa.hdr.NumQueue = 123456789;
        sctpa.hdr.NumAcc = 123456789;
        sctpa.hdr.CheckSum = 12345;
        sctpa.hdr.UrgPtr = 0;
        sctpa.body = new String("Hello SCTPA!");
        
        sctp.body = sctpa;
        sca_master.body = sctp;
        System.out.println("==================== SCTPA TEST =====================");
        System.out.println(sca_master);
        
        System.out.println("==================== PING TEST =====================");
        
    }
    
}
