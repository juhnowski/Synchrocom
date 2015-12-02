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
package synchrocom.sca.ping;

import synchrocom.sca.SCA;
import synchrocom.sca.core.Core;
import synchrocom.scmp.TimeStamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static synchrocom.sca.SCAUtil.EMPTY_HOST_ADDRESS;
import static synchrocom.scmp.SCMPUtil.REQ_TIME_CODE;
import static synchrocom.scmp.SCMPUtil.REQ_TIME_TYPE;

/**
 * Проверка скорости связи до конкретного узла
 */
public class Ping {
    private static volatile Ping instance;
    private static volatile SCA sca = new SCA();
    private static ExecutorService executor;
    public static int MAX_PING_FIXED_THREAD_POOL = 5;
    
    public static Ping getInstance() {
        Ping localInstance = instance;
        if (localInstance == null) {
            synchronized (Ping.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Ping();
                }
            }
            executor = Executors.newFixedThreadPool(MAX_PING_FIXED_THREAD_POOL);
        }
        return localInstance;
    }
    
    /**
     * Отправка тестового пинга самому себе
     */
    public synchronized void send(){
        send((byte)0,EMPTY_HOST_ADDRESS,(short)0,(short)0,(short)0,(byte)10,(byte)1,(short)12345);
    }
    
    public synchronized void send(byte SourceAddress, byte DestinationAddress, short TotalLength, 
            short Identification, short Flags, byte TTL, byte Protocol, 
            short HeaderChecksum){
        
        sca.hdr.SourceAddress = SourceAddress;
        sca.hdr.DestinationAddress = DestinationAddress;
        sca.hdr.TotalLength = TotalLength;
        sca.hdr.Identification = Identification;
        sca.hdr.Flags = Flags;
        sca.hdr.TTL = TTL;
        sca.hdr.Protocol = Protocol;
        sca.hdr.HeaderChecksum = HeaderChecksum;
         
        TimeStamp body = new TimeStamp(REQ_TIME_TYPE, REQ_TIME_CODE);
        sca.body = body;
        Core.getInstance().send(sca);
    }
            
    public synchronized void onReceive(SCA message){
        Runnable worker = new WorkerThread(message);
        executor.execute(worker);
    }
}
