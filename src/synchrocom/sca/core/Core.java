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
package synchrocom.sca.core;

import synchrocom.sca.SCA;
import static synchrocom.sca.SCAUtil.EMPTY_HOST_ADDRESS;
import synchrocom.sca.ping.Ping;
/**
 * Синглтон приложение базовой службы SCA протокола
 */
public class Core {
    
    private static volatile Core instance;
    private static volatile SCA sca;
    
    public static Core getInstance() {
        Core localInstance = instance;
        if (localInstance == null) {
            synchronized (Core.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Core();
                }
            }
        }
        if(sca == null){
            sca = new SCA();
        }
        return localInstance;
    }
    
    public void send(SCA msg){
        if (msg == null){
        System.out.println("CORE send null msg");
        }
        
        send(msg.hdr.SourceAddress, msg.hdr.DestinationAddress, msg.hdr.TotalLength, 
            msg.hdr.Identification, msg.hdr.Flags, msg.hdr.TTL, msg.hdr.Protocol, 
            msg.hdr.HeaderChecksum, msg.body);
    }
            
    public synchronized void send(byte SourceAddress, byte DestinationAddress, short TotalLength, 
            short Identification, short Flags, byte TTL, byte Protocol, 
            int HeaderChecksum, Object body){
        if(sca == null){
            System.err.println("sca null");
        }

        if(sca.hdr == null){
            System.err.println("sca.hdr null");
        }
        
        sca.hdr.SourceAddress = SourceAddress;
        sca.hdr.DestinationAddress = DestinationAddress;
        sca.hdr.TotalLength = TotalLength;
        sca.hdr.Identification = Identification;
        sca.hdr.Flags = Flags;
        sca.hdr.TTL = TTL;
        sca.hdr.Protocol = Protocol;
        sca.hdr.HeaderChecksum = HeaderChecksum;
        sca.body = body;
        
        if (DestinationAddress == EMPTY_HOST_ADDRESS){
            System.out.println("SCA sended to local loop: \n" + sca.toString());
            System.out.println("____________________________________________________\n");
            onReceive();
        }
    }
            
    public synchronized void  onReceive(){
        Ping.getInstance().onReceive(sca);
    }
}
