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

/**
 *
 * @author 7634
 */
public class SCMPUtil {
        
    /**
     * Эхо-ответ
     */
    public static final byte ECHO_RESP_TYPE = 0, ECHO_RESP_CODE = 0;
    
    /**
     * Зарезервировано
     */
    public static final byte RES_1_TYPE = 1, RES_1_CODE = 0;
    public static final byte RES_2_TYPE = 2, RES_2_CODE = 0;

    /**
     * Адресат недоступен
     */
    public static final byte DEST_NA_TYPE = 3;
        /**
         * Сеть недостижима
         */
        public static final byte DEST_NA_NETWORK_NA = 0;
        
        /**
         * Узел недостижим
         */
        public static final byte DEST_NA_HOST_NA = 1;
        
        /**
         * Протокол недостижим
         */
        public static final byte DEST_NA_PROTOCOL_NA = 2;
        
        /**
         * Порт недостижим
         */
        public static final byte DEST_NA_PORT_NA = 3;
        
        /**
         * Необходима фрагментация, но установлен флаг ее запрета (DF)
         */
        public static final byte DEST_NA_DF_NA = 4;
        
        /**
         * Неверный маршрут от источника
         */
        public static final byte DEST_NA_SRC_TRACE_NA = 5;
        
        /**
         * Узел назначения неизвестен
         */
        public static final byte DEST_HOST_UNKNOWN = 6;
        
        /**
         * Узел источник изолирован
         */
        public static final byte DEST_SRC_HOST_ISOLATED = 7;
        
        /**
         * Сеть административно запрещена
         */
        public static final byte DEST_NETWORK_ADM_FBD = 8;
        
        /**
         * Узел административно запрещен
         */
        public static final byte DEST_HOST_ADM_FBD = 9;
        
        /**
         * Коммуникации административно запрещены
         */
        public static final byte DEST_COMM_ADM_FBD = 10;
    
    /**
     * Сдерживание источника(отключение источника при переполнении очереди)
     */
    public static final byte SRC_NA_TYPE = 4, SRC_NA_CODE = 0;
    
    /**
     * Перенаправление
     */
    public static final byte REDIRECT_TYPE = 5;
    
        /**
         * Перенаправление пакетов в сеть CAN
         */
        public static final byte REDIRECT_CAN_NETWORK = 0;
        
        /**
         * Перенаправление пакетов к узлу CAN
         */
        public static final byte REDIRECT_CAN_HOST = 1;
        
    /**
     * Альтернативный адрес узла
     */
    public static final byte SRC_ALT_ADDR_TYPE = 6, SRC_ALT_ADDR_CODE = 0;   
    
    /**
     * Зарезервировано
     */
    public static final byte RES_7_TYPE = 7, RES_7_CODE = 0;
    
    /**
     * Эхо-запрос
     */
    public static final byte ECHO_REQ_TYPE = 8, ECHO_REQ_CODE = 0;
    
    /**
     * Объявление блока разделения сетей
     */
    public static final byte BRS_TYPE = 9, BRS_CODE = 0;
    
    /**
     * Запрос маршрутизатора
     */
    public static final byte ROUTER_REQ_TYPE = 10, ROUTER_REQ_CODE = 0;
    
    /**
     * Время жизни датаграммы истекло
     */
    public static final byte TTL_TYPE = 11;
    
        /**
         * Время жизни пакета (TTL) истекло при транспортировке
         */
         public static final byte TTL_TRANSP_CODE = 0;
         
         /**
          * Время жизни пакета истекло при сборке фрагментов
          */
         public static final byte TTL_FRAGM_CODE = 1;
         
    /**
     * Неверный параметр (проблема с параметрами датаграммы: ошибка в SCA 
     * заголовке или отсутствует необходимая опция)
     */
    public static final byte WRONG_PARAM_TYPE = 12;
    
        /**
         * Указатель говорит об ошибке
         */
        public static final byte ERR_PTR_CODE = 0;
         
        /**
         * Отсутствует требуемая опция
         */
        public static final byte REQ_OPT_NA_CODE = 1;
        
        /**
         * Некорректная длина
         */
        public static final byte WRONG_LEN_CODE = 2;

    /**
     * Запрос метки времени
     */    
    public static final byte REQ_TIME_TYPE = 13, REQ_TIME_CODE = 0;
    
    /**
     * Ответ с меткой времени
     */
    public static final byte RESP_TIME_TYPE = 14, RESP_TIME_CODE = 0;
    
    /**
     * Информационный запрос
     */
    public static final byte REQ_INF_TYPE = 15, REQ_INF_CODE = 0;
    
    /**
     * Информационный ответ
     */
    public static final byte RESP_INF_TYPE = 16, RESP_INF_CODE = 0;
    
    /**
     * Зарезервировано для обеспечения безопасности и экспериментов на устойчивость к ошибкам
     */
    public static final byte RES_19_TYPE = 19, RES_19_CODE = 0;
    public static final byte RES_20_TYPE = 20, RES_20_CODE = 0;
    public static final byte RES_21_TYPE = 21, RES_21_CODE = 0;
    public static final byte RES_22_TYPE = 22, RES_22_CODE = 0;
    public static final byte RES_23_TYPE = 23, RES_23_CODE = 0;
    public static final byte RES_24_TYPE = 24, RES_24_CODE = 0;
    public static final byte RES_25_TYPE = 25, RES_25_CODE = 0;
    public static final byte RES_26_TYPE = 26, RES_26_CODE = 0;
    public static final byte RES_27_TYPE = 27, RES_27_CODE = 0;
    public static final byte RES_28_TYPE = 28, RES_28_CODE = 0;
    public static final byte RES_29_TYPE = 29, RES_29_CODE = 0;
    
    /**
     * Трассировка маршрута в блоке разделения сетей
     */
    public static final byte ROUTE_BRS_TYPE = 30;
    
        /**
         * Исходящий пакет успешно отправлен
         */
        public static final byte ROUTE_BRS_OK_CODE = 0;
        
        /**
         * Путь для исходящего пакета не найден, пакет уничтожен
         */
        public static final byte ROUTE_BRS_PATH_NOT_FOUND = 1;
        
    /**
     * Ошибка преобразования датаграммы
     */
    public static final byte DECODE_ERR_TYPE = 31;
    
        /**
         * Неизвестная или неуказанная ошибка
         */
        public static final byte UNKNOWN_ERROR = 0;
        
        /**
         * Невозможно конвертировать опцию
         */
        public static final byte DECODE_ERROR = 1;
        
        /**
         * Неизвестная обязательная опция
         */
        public static final byte UNKNOWN_REQ_OPT = 2;
        
        /**
         * Неподдерживаемая обязательная опция
         */
        public static final byte UNSUPPORT_REQ_OPT = 3;
        
        /**
         * Неподдерживаемый транспортный протокол
         */
        public static final byte UNSUPPORT_TRANSP_PROT = 4;
        
        /**
         * Превышена полная длина
         */
        public static final byte MAX_LEN_ERR = 5;
        
        /**
         * Превышена длина заголовка SCA
         */
        public static final byte LEN_SCA_HDR_ERR = 6;
        
        /**
         * Номер транспортного протокола больше 255
         */
        public static final byte NUM_TRANSP_MORE_255 = 7;
        
        /**
         * Номер порта вне допустимого диапазона
         */
        public static final byte NUM_PORT_ERR = 8;
        
        /**
         * Превышена длина заголовка транспортного протокола
         */
        public static final byte HDR_LEN_TRANSP_ERR = 9;
        
        /**
         * Переход через границу 32 бит и установлен бит ACK
         */
        public static final byte ACK_32_ERR = 10;
        
        /**
         * Неизвестная обязательная опция транспортного протокола
         */
        public static final byte UNSUPPORT_REQ_TRANSP_OPT = 11;
}
