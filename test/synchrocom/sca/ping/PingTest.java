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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import synchrocom.sca.SCA;
import static synchrocom.sca.SCAUtil.EMPTY_HOST_ADDRESS;

/**
 *
 * @author 7634
 */
public class PingTest {
    
    public PingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class Ping.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Ping result = Ping.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of send method, of class Ping.
     */
    @Test
    public void testSend_0args() {
        System.out.println("send");
        Ping instance = new Ping();
        instance.send();
    }

    /**
     * Test of send method, of class Ping.
     */
    @Test
    public void testSend_8args() {
        System.out.println("send");
        byte SourceAddress = 0;
        byte DestinationAddress = EMPTY_HOST_ADDRESS;
        short TotalLength = 0;
        short Identification = 0;
        short Flags = 0;
        byte TTL = 0;
        byte Protocol = 0;
        short HeaderChecksum = 0;
        Ping instance = new Ping();
        instance.send(SourceAddress, DestinationAddress, TotalLength, Identification, Flags, TTL, Protocol, HeaderChecksum);
    }

    /**
     * Test of onReceive method, of class Ping.
     */
/*    @Test
    public void testOnReceive() {
        System.out.println("onReceive");
        SCA message = null;
        Ping instance = new Ping();
        instance.onReceive(message);
    }
*/    
}
