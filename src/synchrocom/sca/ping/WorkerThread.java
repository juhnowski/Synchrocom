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

import synchrocom.sca.core.Core;
import synchrocom.sca.SCA;
import synchrocom.scmp.TimeStamp;
import static synchrocom.sca.SCAUtil.getCurrentTime;
import static synchrocom.scmp.SCMPUtil.REQ_TIME_CODE;
import static synchrocom.scmp.SCMPUtil.REQ_TIME_TYPE;
import static synchrocom.scmp.SCMPUtil.RESP_TIME_CODE;
import static synchrocom.scmp.SCMPUtil.RESP_TIME_TYPE;
/**
 *
 * @author 7634
 */
public class WorkerThread implements Runnable{
    private SCA msg;
    
    public WorkerThread(SCA message){
        this.msg = message;
    }
    
    public void run(){
        System.out.println(Thread.currentThread().getName() + " processing SCA: \n" + msg);
        process();
        System.out.println("____________________________________________________");
    }
    
    private void process(){
        TimeStamp ts = new TimeStamp(REQ_TIME_TYPE, REQ_TIME_CODE);
        ts = ts.getClass().cast(msg.body);
        if (ts!=null){
            if (ts.SendTime == 0) {
                ts.SendTime = getCurrentTime();
                ts.hdr.Code = RESP_TIME_CODE;
                ts.hdr.Type = RESP_TIME_TYPE;
            } else 
                if (ts.ReceiveTimeByReceiver == 0) {
                    ts.ReceiveTimeByReceiver = getCurrentTime();
                    ts.hdr.Code = REQ_TIME_CODE;
                    ts.hdr.Type = REQ_TIME_TYPE;
                } else
                    if (ts.ReceiveTimeBySender == 0) {
                        ts.ReceiveTimeBySender = getCurrentTime();
                        ts.hdr.Code = RESP_TIME_CODE;
                        ts.hdr.Type = RESP_TIME_TYPE;
                    } else {
                        int currentTime = getCurrentTime();
                        int pingResult = 
                                ((ts.SendTime - ts.ReceiveTimeByReceiver) +
                                (ts.ReceiveTimeByReceiver - ts.ReceiveTimeBySender) +
                                (ts.ReceiveTimeBySender - currentTime)) / 3;
                        System.out.println("Ping to " + msg.hdr.DestinationAddress + " time:" + pingResult);
                        return;
                    }
            Core.getInstance().send(msg);
        }
    }
}
