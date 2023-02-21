import { Space } from 'antd';
import { QuestionCircleOutlined } from '@ant-design/icons';
import React from 'react';
import { useModel, SelectLang } from 'umi';
import Avatar from './AvatarDropdown';
import HeaderSearch from '../HeaderSearch';
import styles from './index.less';
import NoticeIconView from '../NoticeIcon';
import {Access, useAccess} from "@@/plugin-access/access";
export type SiderTheme = 'light' | 'dark';

const GlobalHeaderRight: React.FC = () => {
  const { initialState } = useModel('@@initialState');
  const access = useAccess();

  if (!initialState || !initialState.settings) {
    return null;
  }

  const { navTheme, layout } = initialState.settings;
  let className = styles.right;

  if ((navTheme === 'dark' && layout === 'top') || layout === 'mix') {
    className = `${styles.right}  ${styles.dark}`;
  }

  return (
    <Space className={className}>

      <Access accessible={access.resourceFilter('search@basicGroup')}>
        <HeaderSearch
          className={`${styles.action} ${styles.search}`}
          placeholder="站内搜索"
          defaultValue=""
          options={[
            {
              label: <a href="https://umijs.org/zh/guide/umi-ui.html">umi ui</a>,
              value: 'umi ui',
            },
            {
              label: <a href="next.ant.design">Ant Design</a>,
              value: 'Ant Design',
            },
            {
              label: <a href="https://protable.ant.design/">Pro Table</a>,
              value: 'Pro Table',
            },
            {
              label: <a href="https://prolayout.ant.design/">Pro Layout</a>,
              value: 'Pro Layout',
            },
          ]}
          // onSearch={value => {
          //   console.log('input', value);
          // }}
        />
      </Access>

      <span
        className={styles.action}
        onClick={() => {
          window.open('https://pro.ant.design/docs/getting-started');
        }}
      >
        <QuestionCircleOutlined />
      </span>

      <Access accessible={access.resourceFilter('notice@basicGroup')}>
        <NoticeIconView/>
      </Access>

      <Avatar menu />

      <Access accessible={access.resourceFilter('lang@basicGroup')}>
        <SelectLang className={styles.action} />
      </Access>

    </Space>
  );
};

export default GlobalHeaderRight;
