import { createRequire } from 'module'
import { defineConfig, type DefaultTheme } from 'vitepress'

const require = createRequire(import.meta.url)
const pkg = require('../../package.json')

export const en = defineConfig({
  lang: 'en-US',
  description: 'Vite & Vue powered static site generator.',

  themeConfig: {
    nav: nav(),

    sidebar: {
      '/en/guide/': { base: '/en/guide/', items: sidebarGuide() },
      '/en/other/': { base: '/en/other/', items: sidebarOther() }
    },

    editLink: {
      pattern: 'https://github.com/bestaone/HiAuth/edit/main/docs/:path',
      text: 'Edit this page on GitHub'
    },

    footer: {
      message: 'Released under the MIT License.',
      copyright: `Copyright © 2024-${new Date().getFullYear()} Earven`
    }
  }
})

function nav(): DefaultTheme.NavItem[] {
  return [
    {
      text: 'Guide',
      link: '/en/guide/what-is-hiauth',
      activeMatch: '/en/guide/'
    },
    {
      text: 'Other',
      link: '/en/other/support',
      activeMatch: '/en/other/'
    },
    {
      text: pkg.version,
      items: [
        {
          text: 'Changelog',
          link: 'https://github.com/bestaone/HiAuth/blob/main/CHANGELOG.md'
        },
        {
          text: 'Contributing',
          link: 'https://github.com/bestaone/HiAuth/blob/main/.github/contributing.md'
        }
      ]
    }
  ]
}

function sidebarGuide(): DefaultTheme.SidebarItem[] {
  return [
    {
      text: 'Introduction',
      collapsed: false,
      items: [
        { text: 'What is HiAuth', link: 'what-is-hiauth' },
        { text: 'Quick Start', link: 'quick-start' }
      ]
    },
    {
      text: 'Deployment & Integration',
      collapsed: false,
      items: [
        { text: 'SaaS Edition', link: 'saas' },
        { text: 'Docker Edition', link: 'docker' },
        { text: 'Source Code Edition', link: 'sourcecode' },
        { text: 'hiauth-client Integration', link: 'hiauth-client' },
        { text: 'Deploying on K8S', link: 'k8s' },
        { text: 'Integration Testing', link: 'test' }
      ]
    },
    {
      text: 'Secondary Development',
      collapsed: false,
      items: [
        { text: 'Project Structure', link: 'project' },
        { text: 'Front-end Development', link: 'frontend' },
        { text: 'Backend Development', link: 'backend' }
      ]
    },
    {
      text: 'Other',
      collapsed: false,
      items: [
        { text: 'About Topic', link: 'about-topic' },
        { text: 'Issue', link: 'issue' }
      ]
    }
  ]
}

function sidebarOther(): DefaultTheme.SidebarItem[] {
  return [
    {
      text: 'other',
      items: [
        { text: 'support', link: 'support' },
        { text: 'community', link: 'community' }
      ]
    }
  ]
}
