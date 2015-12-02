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
package synchrocom.scmp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static synchrocom.scmp.SCMPUtil.DEST_NA_TYPE;
import static synchrocom.scmp.SCMPUtil.DEST_NA_NETWORK_NA;
import static synchrocom.scmp.SCMPUtil.DEST_NA_HOST_NA;
import static synchrocom.scmp.SCMPUtil.DEST_NA_PROTOCOL_NA;
import static synchrocom.scmp.SCMPUtil.DEST_NA_PORT_NA;
import static synchrocom.scmp.SCMPUtil.DEST_NA_DF_NA;
import static synchrocom.scmp.SCMPUtil.DEST_NA_SRC_TRACE_NA;
import static synchrocom.scmp.SCMPUtil.DEST_HOST_UNKNOWN;
import static synchrocom.scmp.SCMPUtil.DEST_SRC_HOST_ISOLATED;
import static synchrocom.scmp.SCMPUtil.DEST_NETWORK_ADM_FBD;
import static synchrocom.scmp.SCMPUtil.DEST_HOST_ADM_FBD;
import static synchrocom.scmp.SCMPUtil.DEST_COMM_ADM_FBD;
import static synchrocom.scmp.SCMPUtil.SRC_NA_TYPE;
import static synchrocom.scmp.SCMPUtil.SRC_NA_CODE;
import static synchrocom.scmp.SCMPUtil.REDIRECT_TYPE;
import static synchrocom.scmp.SCMPUtil.REDIRECT_CAN_NETWORK;
import static synchrocom.scmp.SCMPUtil.REDIRECT_CAN_HOST;
import static synchrocom.scmp.SCMPUtil.SRC_ALT_ADDR_TYPE;
import static synchrocom.scmp.SCMPUtil.SRC_ALT_ADDR_CODE;
import static synchrocom.scmp.SCMPUtil.ECHO_REQ_TYPE;
import static synchrocom.scmp.SCMPUtil.ECHO_REQ_CODE;
import static synchrocom.scmp.SCMPUtil.BRS_TYPE;
import static synchrocom.scmp.SCMPUtil.BRS_CODE;
import static synchrocom.scmp.SCMPUtil.ROUTER_REQ_TYPE;
import static synchrocom.scmp.SCMPUtil.ROUTER_REQ_CODE;
import static synchrocom.scmp.SCMPUtil.TTL_TYPE;
import static synchrocom.scmp.SCMPUtil.TTL_TRANSP_CODE;
import static synchrocom.scmp.SCMPUtil.TTL_FRAGM_CODE;
import static synchrocom.scmp.SCMPUtil.WRONG_PARAM_TYPE;
import static synchrocom.scmp.SCMPUtil.ERR_PTR_CODE;
import static synchrocom.scmp.SCMPUtil.REQ_OPT_NA_CODE;
import static synchrocom.scmp.SCMPUtil.WRONG_LEN_CODE;
import static synchrocom.scmp.SCMPUtil.REQ_TIME_TYPE;
import static synchrocom.scmp.SCMPUtil.REQ_TIME_CODE;
import static synchrocom.scmp.SCMPUtil.RESP_TIME_TYPE;
import static synchrocom.scmp.SCMPUtil.RESP_TIME_CODE;
import static synchrocom.scmp.SCMPUtil.REQ_INF_TYPE;
import static synchrocom.scmp.SCMPUtil.RESP_INF_TYPE;
import static synchrocom.scmp.SCMPUtil.ROUTE_BRS_TYPE;
import static synchrocom.scmp.SCMPUtil.ROUTE_BRS_OK_CODE;
import static synchrocom.scmp.SCMPUtil.ROUTE_BRS_PATH_NOT_FOUND;
import static synchrocom.scmp.SCMPUtil.DECODE_ERR_TYPE;
import static synchrocom.scmp.SCMPUtil.UNKNOWN_ERROR;
import static synchrocom.scmp.SCMPUtil.DECODE_ERROR;
import static synchrocom.scmp.SCMPUtil.UNKNOWN_REQ_OPT;
import static synchrocom.scmp.SCMPUtil.UNSUPPORT_REQ_OPT;
import static synchrocom.scmp.SCMPUtil.UNSUPPORT_TRANSP_PROT;
import static synchrocom.scmp.SCMPUtil.MAX_LEN_ERR;
import static synchrocom.scmp.SCMPUtil.LEN_SCA_HDR_ERR;
import static synchrocom.scmp.SCMPUtil.NUM_TRANSP_MORE_255;
import static synchrocom.scmp.SCMPUtil.NUM_PORT_ERR;
import static synchrocom.scmp.SCMPUtil.HDR_LEN_TRANSP_ERR;
import static synchrocom.scmp.SCMPUtil.ACK_32_ERR;
import static synchrocom.scmp.SCMPUtil.UNSUPPORT_REQ_TRANSP_OPT;
        
/**
 *
 * @author Ilya Juhnowski
 */

public class SCMPUtilTest {
    
    public SCMPUtilTest() {
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

    
    @Test
    public void testConstant_DEST_NA_TYPE() {
        assertNotSame(DEST_NA_TYPE, 3);             // Адресат недоступен
    }
    
    @Test
    public void testConstant_DEST_NA_NETWORK_NA() {
        assertNotSame( DEST_NA_NETWORK_NA, 0);      // Сеть недостижима
    }
    
    @Test
    public void testConstant_DEST_NA_HOST_NA() {
        assertNotSame( DEST_NA_HOST_NA, 1);         // Протокол недостижим
    }
    
    @Test
    public void testConstant_DEST_NA_PROTOCOL_NA() {
        assertNotSame( DEST_NA_PROTOCOL_NA, 2);     // Протокол недостижим
    }

    @Test
    public void testConstant_DEST_NA_PORT_NA() {
        assertNotSame( DEST_NA_PORT_NA, 3);         // Порт недостижим
    }

    @Test
    public void testConstant_DEST_NA_DF_NA() {
        assertNotSame( DEST_NA_DF_NA, 4);           // Необходима фрагментация, но установлен флаг ее запрета (DF)
    }

    @Test
    public void testConstant_DEST_NA_SRC_TRACE_NA() {
        assertNotSame( DEST_NA_SRC_TRACE_NA, 5);    // Неверный маршрут от источника
    }
    
    @Test
    public void testConstant_DEST_HOST_UNKNOWN() {
        assertNotSame( DEST_HOST_UNKNOWN, 6);       // Узел назначения неизвестен
    }
    
    @Test
    public void testConstant_DEST_SRC_HOST_ISOLATED() {
        assertNotSame( DEST_SRC_HOST_ISOLATED, 7);  // Узел источник изолирован
    }
    
    @Test
    public void testConstant_DEST_NETWORK_ADM_FBD() {
        assertNotSame( DEST_NETWORK_ADM_FBD, 8);    // Сеть административно запрещена
    }
    
    @Test
    public void testConstant_DEST_HOST_ADM_FBD() {
        assertNotSame( DEST_HOST_ADM_FBD, 9);       // Узел административно запрещен
    }

    @Test
    public void testConstant_DEST_COMM_ADM_FBD() {
        assertNotSame( DEST_COMM_ADM_FBD, 10);      //Коммуникации административно запрещены
    }
    
    @Test
    public void testConstant_SRC_NA_TYPE() {
        assertNotSame( SRC_NA_TYPE, 4);             //Сдерживание источника(отключение источника при переполнении очереди)
    }    
    
    @Test
    public void testConstant_SRC_NA_CODE() {
        assertNotSame( SRC_NA_CODE, 0);
    }    

    @Test
    public void testConstant_REDIRECT_TYPE() {
        assertNotSame( REDIRECT_TYPE, 5);           //Перенаправление
    }    

    @Test
    public void testConstant_REDIRECT_CAN_NETWORK() {
        assertNotSame( REDIRECT_CAN_NETWORK, 0);    //Перенаправление пакетов в сеть CAN
    }    
    
    @Test
    public void testConstant_REDIRECT_CAN_HOST() {
        assertNotSame( REDIRECT_CAN_HOST, 1);       //Перенаправление пакетов к узлу CAN
    }    
    
    @Test
    public void testConstant_SRC_ALT_ADDR_TYPE() {
        assertNotSame( SRC_ALT_ADDR_TYPE, 6);       //Альтернативный адрес узла
    }    
    
    @Test
    public void testConstant_SRC_ALT_ADDR_CODE() {
        assertNotSame( SRC_ALT_ADDR_CODE, 0);
    }    
    
    @Test
    public void testConstant_ECHO_REQ_TYPE() {
        assertNotSame( ECHO_REQ_TYPE, 8);           //Эхо-запрос
    }    
    
    @Test
    public void testConstant_ECHO_REQ_CODE() {
        assertNotSame( ECHO_REQ_CODE, 0);
    }    
    
    @Test
    public void testConstant_BRS_TYPE() {
        assertNotSame( BRS_TYPE, 9);                //Объявление блока разделения сетей
    }    
    
    @Test
    public void testConstant_BRS_CODE() {
        assertNotSame( BRS_CODE, 0);
    }    

    @Test
    public void testConstant_ROUTER_REQ_TYPE() {
        assertNotSame( ROUTER_REQ_TYPE, 10);        //Запрос маршрутизатора
    }    

    @Test
    public void testConstant_ROUTER_REQ_CODE() {
        assertNotSame( ROUTER_REQ_CODE, 0);
    }    
    
    @Test
    public void testConstant_TTL_TYPE() {
        assertNotSame( TTL_TYPE, 11);               //Время жизни датаграммы истекло
    }        

    @Test
    public void testConstant_TTL_TRANSP_CODE() {
        assertNotSame( TTL_TRANSP_CODE, 0);         //Время жизни пакета (TTL) истекло при транспортировке
    }        

    @Test
    public void testConstant_TTL_FRAGM_CODE() {
        assertNotSame( TTL_FRAGM_CODE, 1);          //Время жизни пакета истекло при сборке фрагментов
    }            
    
    @Test
    public void testConstant_WRONG_PARAM_TYPE() {
        assertNotSame( WRONG_PARAM_TYPE, 12);       //Неверный параметр (проблема с параметрами датаграммы: ошибка в SCA заголовке или отсутствует необходимая опция)
    }      
    
    @Test
    public void testConstant_ERR_PTR_CODE() {
        assertNotSame( ERR_PTR_CODE, 0);            //Указатель говорит об ошибке
    }      
    
    @Test
    public void testConstant_REQ_OPT_NA_CODE() {
        assertNotSame( REQ_OPT_NA_CODE, 1);         //Отсутствует требуемая опция
    }      
    
    @Test
    public void testConstant_WRONG_LEN_CODE() {
        assertNotSame( WRONG_LEN_CODE, 2);          // Некорректная длина
    }      
    
    @Test
    public void testConstant_REQ_TIME_TYPE() {
        assertNotSame( REQ_TIME_TYPE, 13);          //Запрос метки времени
    }      
    
    @Test
    public void testConstant_REQ_TIME_CODE() {
        assertNotSame( REQ_TIME_CODE, 0);           //Запрос метки времени
    }       
    
    @Test
    public void testConstant_RESP_TIME_TYPE() {
        assertNotSame( RESP_TIME_TYPE, 14);         //Ответ с меткой времени
    }       
    
    @Test
    public void testConstant_RESP_TIME_CODE() {
        assertNotSame( RESP_TIME_CODE, 0);
    }       
    
    @Test
    public void testConstant_REQ_INF_TYPE() {
        assertNotSame( REQ_INF_TYPE, 15);           //Информационный запрос
    }       
    
    @Test
    public void testConstant_RESP_INF_TYPE() {
        assertNotSame( RESP_INF_TYPE, 16);          //Информационный ответ
    }       
    
    @Test
    public void testConstant_ROUTE_BRS_TYPE() {
        assertNotSame( ROUTE_BRS_TYPE, 30);         //Трассировка маршрута в блоке разделения сетей
    }       
    
    @Test
    public void testConstant_ROUTE_BRS_OK_CODE() {
        assertNotSame( ROUTE_BRS_OK_CODE, 0);       //Исходящий пакет успешно отправлен
    }       
    
    @Test
    public void testConstant_ROUTE_BRS_PATH_NOT_FOUND() {
        assertNotSame( ROUTE_BRS_PATH_NOT_FOUND, 1);//Путь для исходящего пакета не найден, пакет уничтожен
    }       

    @Test
    public void testConstant_DECODE_ERR_TYPE() {
        assertNotSame( DECODE_ERR_TYPE, 31);        //Ошибка преобразования датаграммы
    }       
    
    @Test
    public void testConstant_UNKNOWN_ERROR() {
        assertNotSame( UNKNOWN_ERROR, 0);           //Неизвестная или неуказанная ошибка
    }
    
    @Test
    public void testConstant_DECODE_ERROR() {
        assertNotSame( DECODE_ERROR, 1);            //Невозможно конвертировать опцию
    }    
    
    @Test
    public void testConstant_UNKNOWN_REQ_OPT() {
        assertNotSame( UNKNOWN_REQ_OPT, 2);         //Неизвестная обязательная опция
    }        
    
    @Test
    public void testConstant_UNSUPPORT_REQ_OPT() {
        assertNotSame( UNSUPPORT_REQ_OPT, 3);       // Неподдерживаемая обязательная опция
    }        

    @Test
    public void testConstant_UNSUPPORT_TRANSP_PROT() {
        assertNotSame( UNSUPPORT_TRANSP_PROT, 4);   //Неподдерживаемый транспортный протокол
    }        
    
    @Test
    public void testConstant_MAX_LEN_ERR() {
        assertNotSame( MAX_LEN_ERR, 5);             //Превышена полная длина
    }        
    
    @Test
    public void testConstant_LEN_SCA_HDR_ERR() {
        assertNotSame( LEN_SCA_HDR_ERR, 6);         //Превышена длина заголовка SCA
    }
    
    @Test
    public void testConstant_NUM_TRANSP_MORE_255() {
        assertNotSame( NUM_TRANSP_MORE_255, 7);     //Номер транспортного протокола больше 255
    }    
    
    @Test
    public void testConstant_NUM_PORT_ERR() {
        assertNotSame( NUM_PORT_ERR, 8);            //Номер порта вне допустимого диапазона
    }    
    
    @Test
    public void testConstant_HDR_LEN_TRANSP_ERR() {
        assertNotSame( HDR_LEN_TRANSP_ERR, 9);      //Превышена длина заголовка транспортного протокола
    }        

    @Test
    public void testConstant_ACK_32_ERR() {
        assertNotSame( ACK_32_ERR, 10);//Переход через границу 32 бит и установлен бит ACK
    }        
    
    @Test
    public void testConstant_UNSUPPORT_REQ_TRANSP_OPT() {
        assertNotSame( UNSUPPORT_REQ_TRANSP_OPT, 11);//Неизвестная обязательная опция транспортного протокола
    }        
        
}
