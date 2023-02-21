import {Access, useIntl} from 'umi';
import {GithubOutlined} from '@ant-design/icons';
import {DefaultFooter} from '@ant-design/pro-layout';
import {useAccess} from "@@/plugin-access/access";

export default () => {

  const access = useAccess();
  const intl = useIntl();
  const defaultMessage = intl.formatMessage({
    id: 'app.copyright.produced',
    defaultMessage: '天辉科技出品',
  });

  return (
    <Access accessible={access.resourceFilter('footer@basicGroup')}>
      <DefaultFooter
        copyright={`2021 ${defaultMessage}`}
        links={[
          {
            key: 'HiAuth',
            title: 'HiAuth',
            href: 'https://pro.ant.design',
            blankTarget: true,
          },
          {
            key: 'github',
            title: <GithubOutlined />,
            href: 'https://github.com/ant-design/ant-design-pro',
            blankTarget: true,
          },
          {
            key: 'HiMall',
            title: 'HiMall',
            href: 'https://ant.design',
            blankTarget: true,
          },
        ]}
      />
    </Access>
  );

};
