import elementEnLocale from 'element-ui/lib/locale/lang/en'

export default {
  en: {
    ...elementEnLocale,
    layout: {
      search: {
        placeholder: 'Input expert name or query',
        term: 'Term',
        name: 'Name',
        organization: 'Organization'
      },
      rightMenu: {
        switchLanguage: '切换到中文',
        productList: '{productName} Function List',
        channel: 'Channel',
        rankings: 'Rankings',
        thesisRecommends: 'Thesis',
        notLogged: 'Not Logged',
        logIn: 'Log in',
        academicProfile: 'Academic Profile',
        userProfile: 'User Profile',
        myFollowing: 'My Following',
        paperCollections: 'Paper Collections',
        logout: 'Logout'
      },
      footer: {
        protocol: 'Protocol',
        policy: 'Policy',
        manual: 'Manual',
        contact: 'Contact',
        introduction: 'Introduction',
        joinUs: 'Join-us',
        zhipu: 'ZHIPU.AI',
        contactUs: {
          title: 'Contact us',
          contents: 'You may visit {FAQ} to find helpful information immediately before contacting us',
          FAQ: 'FAQ',
          emailContents: 'Email:{email}',
          email: 'Report@aminer.cn'
        }
      },
      loginDialog: {
        SMSLogin: 'SMS login',
        accountLogin: 'Account login',
        phonePlaceholder: 'Please enter phone number',
        phoneError: 'Please enter phone number',
        phoneError2: 'Please enter a valid phone number',
        codePlaceholder: 'Please enter verification code',
        codeError: 'Verification code cannot be empty',
        codeError2: 'Please enter the correct verification code',
        accountPlaceholder: 'Please enter the account',
        passwordPlaceholder: 'Please enter the password',
        passwordError: 'Please enter the password',
        send: 'Send',
        login: 'Login',
        agree: 'Read and agree{policy}and{contact}',
        agreeError: 'User agreement and privacy terms are not checked!',
        policy: 'Policy',
        contact: 'Contact',
        keepLogin: 'Keep me logged in',
        forget: 'Forget the password',
        register: 'Register',
        others: 'Or sign in using',
        SMSNotOpen: 'SMS login is not open'
      }
    },
    scholarDetails: {
      binding: 'Binding',
      professor: 'Professor',
      follow: 'Follow His/Her Research Updates',
      views: 'views：',
      information: 'Information',
      viewMore: 'Sign in to view more',
      experience: 'Experience',
      education: 'Education',
      bio: 'Bio',
      researchInterests: 'Research Interests',
      checkPapers: 'View Papers',
      checkPatents: 'View Patents',
      papers: {
        title: 'Papers',
        total: '{total} papers',
        addPaper: 'Add Paper',
        sort: 'Sort',
        byYear: 'By Year',
        byCitation: 'By Citation',
        year: 'Year',
        all: 'All',
        recent: 'Recent(10)',
        top: 'Top(10)',
        cited: 'cited',
        bibtex: 'Bibtex',
        viewAll: 'View All'
      },
      patents: {
        title: 'Patents',
        total: '{total} patents',
        addPatent: 'Add Patent',
        sort: 'Sort',
        byYear: 'By Year',
        year: 'Year',
        all: 'All',
        recent: 'Recent(10)',
        top: 'Top(10)',
        firstInventor: 'First Inventor',
        viewAll: 'View All'
      },
      egoNetwork: 'Ego Network',
      authorStatistics: 'Author Statistics',
      similarAuthors: 'Similar Authors'
    },
    thesisDetails: {
      AIHelp: 'AI helps you reading Science',
      AIGenerate: {
        title: 'AI generates interpretation videos',
        contents: 'AI extracts and analyses the key points of the paper to generate videos automatically',
        goGenerating: 'Go Generating'
      },
      AITraceability: {
        title: 'AI Traceability',
        contents: 'AI parses the academic lineage of this thesis',
        generateMRT: 'Generate MRT'
      },
      cited: 'Cited: ',
      views: 'Views',
      fullText: 'Full Text',
      viewViaPublisher: 'View Via Publisher',
      otherLinks: 'Other Links',
      bibtex: 'Bibtex',
      mark: 'Mark',
      keywords: 'Keywords',
      abstract: 'Abstract',
      code: 'Code',
      data: 'Data',
      ppt: 'PPT(Upload PPT)',
      uploadPPT: 'Upload PPT',
      updateFullText: 'Update Full Text',
      uploadPDF: 'Upload PDF',
      author: 'Author',
      yourRating: 'Your rating :',
      noRatings: 'No Ratings',
      tags: 'Tags',
      addTags: 'Add tags',
      comments: 'Comments',
      submit: 'Submit'
    }
  }
}
