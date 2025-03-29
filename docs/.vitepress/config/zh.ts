import { createRequire } from 'module'
import { defineConfig, type DefaultTheme } from 'vitepress'

const require = createRequire(import.meta.url)
const pkg = require('../../package.json')

export const zh = defineConfig({
  lang: 'zh-Hans',
  description: '基于OAuth2.0协议的认证授权服务',

  themeConfig: {
    nav: nav(),

    sidebar: {
      '/guide/': { base: '/guide/', items: sidebarGuide() },
      '/other/': { base: '/other/', items: sidebarOther() }
    },

    editLink: {
      pattern: 'https://github.com/bestaone/HiAuth/edit/main/docs/:path',
      text: '在 GitHub 上编辑此页面'
    },

    footer: {
      message: '基于 MIT 许可发布',
      copyright: `版权所有 © 2024-${new Date().getFullYear()} 张国圣`
    },

    docFooter: {
      prev: '上一页',
      next: '下一页'
    },

    outline: {
      label: '页面导航'
    },

    lastUpdated: {
      text: '最后更新于',
      formatOptions: {
        dateStyle: 'short',
        timeStyle: 'medium'
      }
    },

    langMenuLabel: '多语言',
    returnToTopLabel: '回到顶部',
    sidebarMenuLabel: '菜单',
    darkModeSwitchLabel: '主题',
    lightModeSwitchTitle: '切换到浅色模式',
    darkModeSwitchTitle: '切换到深色模式',
    skipToContentLabel: '跳转到内容'
  }
})

function nav(): DefaultTheme.NavItem[] {
  return [
    {
      text: '文档',
      link: '/guide/what-is-hiauth',
      activeMatch: '/guide/'
    },
    {
      text: '其他',
      link: '/other/support',
      activeMatch: '/other/'
    },
    {
      text: pkg.version,
      items: [
        {
          text: '更新日志',
          link: 'https://github.com/bestaone/HiAuth/blob/main/CHANGELOG.md'
        },
        {
          text: '参与贡献',
          link: 'https://github.com/bestaone/HiAuth/blob/main/.github/contributing.md'
        }
      ]
    }
  ]
}

function sidebarGuide(): DefaultTheme.SidebarItem[] {
  return [
    {
      text: '简介',
      collapsed: false,
      items: [
        { text: '什么是HiAuth', link: 'what-is-hiauth' },
        { text: '快速体验', link: 'quick-start' }
      ]
    },
    {
      text: '部署&集成',
      collapsed: false,
      items: [
        { text: 'SaaS版', link: 'saas' },
        { text: 'Docker版', link: 'docker' },
        { text: '源码版', link: 'sourcecode' },
        { text: 'hiauth-client集成', link: 'hiauth-client' },
        { text: 'K8S上部署', link: 'k8s' },
        { text: '集成测试', link: 'test' }
      ]
    },
    {
      text: '二次开发',
      collapsed: false,
      items: [
        { text: '项目结构', link: 'project' },
        { text: '前端开发', link: 'frontend' },
        { text: '后端开发', link: 'backend' }
      ]
    },
    {
      text: '其他',
      collapsed: false,
      items: [
        { text: '相关资料', link: 'about-topic' },
        { text: '问题', link: 'issue' }
      ]
    }
  ]
}

function sidebarOther(): DefaultTheme.SidebarItem[] {
  return [
    {
      text: '其他',
      items: [
        { text: '技术支持', link: 'support' },
        { text: '社区', link: 'community' }
      ]
    }
  ]
}

export const search: DefaultTheme.AlgoliaSearchOptions['locales'] = {
  zh: {
    placeholder: '搜索文档',
    translations: {
      button: {
        buttonText: '搜索文档',
        buttonAriaLabel: '搜索文档'
      },
      modal: {
        searchBox: {
          resetButtonTitle: '清除查询条件',
          resetButtonAriaLabel: '清除查询条件',
          cancelButtonText: '取消',
          cancelButtonAriaLabel: '取消'
        },
        startScreen: {
          recentSearchesTitle: '搜索历史',
          noRecentSearchesText: '没有搜索历史',
          saveRecentSearchButtonTitle: '保存至搜索历史',
          removeRecentSearchButtonTitle: '从搜索历史中移除',
          favoriteSearchesTitle: '收藏',
          removeFavoriteSearchButtonTitle: '从收藏中移除'
        },
        errorScreen: {
          titleText: '无法获取结果',
          helpText: '你可能需要检查你的网络连接'
        },
        footer: {
          selectText: '选择',
          navigateText: '切换',
          closeText: '关闭',
          searchByText: '搜索提供者'
        },
        noResultsScreen: {
          noResultsText: '无法找到相关结果',
          suggestedQueryText: '你可以尝试查询',
          reportMissingResultsText: '你认为该查询应该有结果？',
          reportMissingResultsLinkText: '点击反馈'
        }
      }
    }
  }
}
